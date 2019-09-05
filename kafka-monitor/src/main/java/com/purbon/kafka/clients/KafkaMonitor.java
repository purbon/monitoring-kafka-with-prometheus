package com.purbon.kafka.clients;


import java.io.IOException;
import java.lang.Thread;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaMonitor {

  static class ProducerThread extends Thread {

    private final String kafkaServers;

    public ProducerThread(String kafkaServers) {
      this.kafkaServers = kafkaServers;
    }

    public void run() {
      EventsProducer producer = new EventsProducer(kafkaServers);

      String[] countries = new String[]{"DE", "UK"};

      int numberOfDocs = 100;
      ArrayList<CarEvent> docs = new ArrayList<CarEvent>();
      Random rand = new Random(System.currentTimeMillis());
      int total = 0;
      while(true) {
        for (int i = 0; i < numberOfDocs; i++) {
          long viewTime = System.currentTimeMillis();
          String plateId = StringUtils.leftPad(String.valueOf(Math.abs(rand.nextLong()%100000000)), 8);
          String country = countries[rand.nextInt(countries.length)];
          String plate = plateId+"-"+country;
          CarEvent event = new CarEvent(viewTime, plate);
          docs.add(event);
          try {
            sleep(2);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        try {
          producer.load(docs);
        } catch (IOException e) {
          e.printStackTrace();
        }
        total = total + numberOfDocs;
        System.out.println("Generated: "+total+ "docs");
        try {
          sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }

  }

  private static String getHealthTopic() {
    String topic = System.getenv("HEALTH_TOPIC");
    if (topic == null)
      topic = "my-health-topic";
    return topic;
  }

  static class ConsumerThread extends Thread implements KafkaMonitorBean {

    private final String kafkaServers;
    private Long latency;
    private boolean healthy;

    public ConsumerThread(String kafkaServers) {
      this.kafkaServers = kafkaServers;
      this.latency = 0L;
      this.healthy = false;
    }

    public void run() {
      EventsConsumer eventsConsumer = new EventsConsumer(kafkaServers);
      KafkaConsumer<Long, String> consumer = eventsConsumer.consumer();
      consumer.subscribe(Collections.singleton(getHealthTopic()));

      while(true) {
        ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(1000));

        records.forEach(consumerRecord -> {

          try {
            CarEvent event = eventsConsumer.stringAsCarEvent(consumerRecord.value());
            long latency = System.currentTimeMillis()-event.viewtime;
            setLatency(latency);
            setHealthy(true);
          } catch (IOException e) {
            e.printStackTrace();
            setHealthy(false);
          }

        });

        System.out.println("Records fetched: "+records.count());

        consumer.commitSync();
      }
    }

    @Override
    public void setLatency(Long latency) {
      this.latency = latency;
    }

    @Override
    public Long getLatency() {
      return latency;
    }

    @Override
    public void setHealthy(boolean healthy) {
      this.healthy = healthy;
    }

    @Override
    public boolean getHealth() {
      return healthy;
    }
  }

  public static void main(String[] args) throws Exception {

    String kafkaServers = System.getenv("BOOTSTRAP_SERVERS");
    if (kafkaServers == null)
      kafkaServers = "kafka:9092";

    MBeanServer mbs = null;

    ProducerThread producerThread = new ProducerThread(kafkaServers);
    ConsumerThread consumerThread = new ConsumerThread(kafkaServers);

    ObjectName consumerBeanName = new ObjectName("KafkaMonitor:name=ConsumerLatencyBean");
    mbs.registerMBean(consumerThread, consumerBeanName);

    producerThread.start();
    consumerThread.start();
    consumerThread.join();
  }

}

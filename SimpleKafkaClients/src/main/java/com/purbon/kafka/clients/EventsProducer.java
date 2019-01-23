package com.purbon.kafka.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;

public class EventsProducer {

  private ObjectMapper mapper = new ObjectMapper();

  private Properties configure() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "my-producer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    return props;
  }

  public void load(List<CarEvent> events) throws IOException {

    KafkaProducer<Long, String> producer = new KafkaProducer<>(configure());

    for(CarEvent event : events) {
      String value = mapper.writeValueAsString(event);
      Long k = event.getViewtime();
      ProducerRecord<Long, String> record = new ProducerRecord<>("my-topic", k, value);
      producer.send(record);
    }

    producer.flush();
  }

  public static void main(String[] args) throws Exception {

    EventsProducer producer = new EventsProducer();

    String[] countries = new String[]{"DE", "UK"};

    int numberOfDocs = 100;
    ArrayList<CarEvent> docs = new ArrayList<CarEvent>();
    Random rand = new Random(System.currentTimeMillis());
    int total = 0;
    int count = 0;
    while(true) {
      for (int i = 0; i < numberOfDocs; i++) {
        long viewTime = System.currentTimeMillis();
        String plateId = StringUtils.leftPad(String.valueOf(Math.abs(rand.nextLong()%100000000)), 8);
        String country = countries[rand.nextInt(countries.length)];
        String plate = plateId+"-"+country;
        CarEvent event = new CarEvent(viewTime, plate);
        docs.add(event);
        Thread.sleep(2);
      }
      producer.load(docs);
      total = total + numberOfDocs;
      System.out.println("Generated: "+total+ "docs");
      if (count % 5 == 0) {
        System.out.println("Sleeping 5 seconds");
        Thread.sleep(5000);
      }

      count++;

    }
  }
}

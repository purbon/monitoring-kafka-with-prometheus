package com.purbon.kafka.clients;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;

public class EventsConsumer {

  private ObjectMapper mapper = new ObjectMapper();

  private static Properties configure() {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongDeserializer");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    return props;
  }

  public static void main(String[] args) {


    KafkaConsumer<Long, String> consumer = new KafkaConsumer<>(configure());
    consumer.subscribe(Collections.singletonList("my-topic"));

    while(true) {
      ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(1000));

      records.forEach(consumerRecord -> {
        System.out.println(consumerRecord.key()+" "+consumerRecord.value());
      });

      System.out.println("Records fetched: "+records.count());

      consumer.commitSync();
    }

  }
}

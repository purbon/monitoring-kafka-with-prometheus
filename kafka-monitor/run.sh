#!/usr/bin/env bash

#KAFKA_SERVERS="kafka:9092"

echo "starting up the Kafka Monitor"

java $JMX_OPTS -cp /clients/kafka-monitor.jar com.purbon.kafka.clients.KafkaMonitor
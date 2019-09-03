#!/usr/bin/env bash

#KAFKA_SERVERS="kafka:9092"

echo "Starting up a regular paced $CLIENT_MODE with $KAFKA_SERVERS"

if [ $CLIENT_MODE == "consumer" ]; then
  export JMX_OPTS="-javaagent:${JMX_AGENT_HOME}=0.0.0.0:8077:/etc/prometheus-config/kafka-cli.yml"
  java $JMX_OPTS -cp /clients/kafka-clients.jar com.purbon.kafka.clients.EventsConsumer $KAFKA_SERVERS
else
  export JMX_OPTS="-javaagent:${JMX_AGENT_HOME}=0.0.0.0:8078:/etc/prometheus-config/kafka-cli.yml"
  java $JMX_OPTS -cp /clients/kafka-clients.jar com.purbon.kafka.clients.EventsProducer $KAFKA_SERVERS
fi

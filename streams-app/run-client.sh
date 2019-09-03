#!/usr/bin/env bash

#KAFKA_SERVERS="kafka:9092"

CLIENT_MODE=streams
echo "Starting up a regular paced $CLIENT_MODE with $KAFKA_SERVERS"

export JMX_OPTS="-javaagent:${JMX_AGENT_HOME}=0.0.0.0:8099:/etc/prometheus-config/kafka-streams.yml"
java $JMX_OPTS -cp /clients/kafka-streams.jar com.purbon.kafka.App

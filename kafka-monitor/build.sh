#!/usr/bin/env bash

mvn clean package
mvn assembly:single

docker build -t purbon/kafka-monitor:latest .


# build the producer image

 #docker build -t purbon/kafka-client-producer:latest  .

# build the consunmer image

#docker build -t purbon/kafka-client-consumer:latest .

#  --build-arg CLIENT_MODE=consumer

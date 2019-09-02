#!/usr/bin/env bash

mvn clean package
mvn assembly:single

# build the producer image

 docker build -t purbon/kafka-client-producer:latest --build-arg CLIENT_MODE=producer .

# build the consunmer image

docker build -t purbon/kafka-client-consumer:latest --build-arg CLIENT_MODE=consumer .

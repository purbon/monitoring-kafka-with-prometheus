#!/usr/bin/env bash

function packagejar() {
  mvn clean package
  mvn assembly:single
}

#(cd app; packagejar)

docker build -t purbon/kafka-streams-app:latest .

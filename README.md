# Monitoring Apache Kafka with Prometheus


## What can you find in this repo?

This repository contains the building blocs and configurations necessary to setup monitoring with Prometheus for a running Apache Kafka cluster as well as a dummy clients (producers and consumers) that could be used to test the monitoring setup.

### Building blocks

Inside the [SimpleKafkaClients/](SimpleKafkaClients/) directory you will
find a collection of dummy kafka clients that could be used to verify
the overall monitoring setup.

As well inside:

* The [docker-kafka-cluster/](docker-kafka-cluster) directory contains
  as well a dummy kafka cluster that could be used during the setup and
  configuration of this monitoring example.
* The [prometheus/](prometheus/) directory contains the necessary
  building blocks to setup the a dockerized prometheus instance as well
  of using the jmx_exporter to be able to pull jmx metrics out of the
  kafka clients and the brokers into prometheus.

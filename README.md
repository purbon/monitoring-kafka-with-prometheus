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
  of using jmx_exporter to be able to pull jmx metrics out of the
  kafka clients and the brokers into prometheus.


## How to use this blocs

In a local environment, mostly with the objective to show and tell the
prometheus setup, you could in case of need, start the local Apache Kafka cluster. You can do that by following the commands explained in dummy docker-kafka-cluster/README.md file.

Once you have a local cluster the next steps are to setup the prometheus
environment, while in production you will set this with something like
kubernetes, in the case of this repo we'll use again docker compose. You
will find all necessary bits explained at the prometheus/README.md file.

At the end, only thing missed is to generate traffic, for this you can
leverage either your own applications or the example code located at [SimpleKafkaClients/](SimpleKafkaClients/).


## Contributing

All contributions are welcome: ideas, patches, documentation, bug reports,
complaints, etc!

Programming is not a required skill, and there are many ways to help out!
It is more important to us that you are able to contribute.

That said, [some basic guidelines](CONTRIBUTING.md), which you are free to ignore :)

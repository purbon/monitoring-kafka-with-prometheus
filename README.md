# Monitoring Apache Kafka with Prometheus


## What can you find in this repo?

This repository contains the building blocs and configurations necessary to setup monitoring with Prometheus for a running Apache Kafka cluster as well as a dummy clients (producers, consumers and kafka streams) that could be used to test the monitoring setup.

### Building blocks


* Graphana
* The [prometheus/](prometheus/) directory contains the necessary
  building blocks to setup the a dockerized prometheus instance as well
  of using jmx_exporter to be able to pull jmx metrics out of the
  kafka clients and the brokers into prometheus.
* And a bunch kafka clients (consumers, producers)

## How to use this blocs

In a local environment, mostly with the objective to show and tell the
prometheus setup, run:

```bash
 docker-compose up -d --build
 ```

and open:

* http://localhost:3000 for Graphana
* http://localhost:9090 for Prometheus

## Contributing

All contributions are welcome: ideas, patches, documentation, bug reports,
complaints, etc!

Programming is not a required skill, and there are many ways to help out!
It is more important to us that you are able to contribute.

That said, [some basic guidelines](CONTRIBUTING.md), which you are free to ignore :)

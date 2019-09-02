#!/usr/bin/env bash
# Script to run a java application for testing jmx4prometheus.


export JMX_EXPORTER_HOME="/Users/pere/work/monitoring/prometheus/jmx_exporter"

export KAFKA_OPTS="-javaagent:$JMX_EXPORTER_HOME/jmx_prometheus_javaagent-0.3.1.jar=0.0.0.0:8079:$JMX_EXPORTER_HOME/kafka.yml"

export PROME_OPTS="-javaagent:$JMX_EXPORTER_HOME/jmx_prometheus_javaagent-0.3.1.jar=0.0.0.0:8078:$JMX_EXPORTER_HOME/kafka-cli.yml"


export JMX_OPTS="-Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=5555"

# Note: You can use localhost:5556 instead of 5556 for configuring socket hostname.
#java -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=5555 -jar jmx_prometheus_httpserver/target/jmx_prometheus_httpserver-${version}-jar-with-dependencies.jar 5556 example_configs/httpserver_sample_config.yml

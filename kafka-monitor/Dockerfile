FROM centos
MAINTAINER pere.urbon@gmail.com
ENV container docker
ENV JMX_AGENT_HOME /jmx-reporter/jmx_prometheus_javaagent.jar

RUN yum install -y java-11-openjdk
COPY target/SimpleKafkaClients-1.0-jar-with-dependencies.jar /clients/kafka-clients.jar
COPY run-client.sh /clients/run-client.sh

COPY prometheus/kafka-cli.yml /etc/prometheus-config/kafka-cli.yml
COPY jmx-reporter/jmx_prometheus_javaagent-0.3.1.jar "${JMX_AGENT_HOME}"

ENTRYPOINT ["/clients/run-client.sh", "${CLIENT_MODE}"]

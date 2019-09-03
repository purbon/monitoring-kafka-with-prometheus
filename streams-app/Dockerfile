FROM centos
MAINTAINER pere.urbon@gmail.com
ENV container docker
ENV JMX_AGENT_HOME /jmx-reporter/jmx_prometheus_javaagent.jar

RUN yum install -y java-11-openjdk
COPY app/target/app-1.0-jar-with-dependencies.jar /clients/kafka-streams.jar
COPY run-client.sh /clients/run-client.sh

COPY prometheus/kafka-streams.yml /etc/prometheus-config/kafka-streams.yml
COPY jmx-reporter/jmx_prometheus_javaagent-0.3.1.jar "${JMX_AGENT_HOME}"

ENTRYPOINT ["/clients/run-client.sh", "${CLIENT_MODE}"]

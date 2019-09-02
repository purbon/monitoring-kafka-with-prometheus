FROM centos
MAINTAINER pere.urbon@gmail.com
ENV container docker

# 1. Install openjdk
# 1. Adding Confluent repository
RUN rpm --import https://packages.confluent.io/rpm/5.2/archive.key
COPY confluent.repo /etc/yum.repos.d/confluent.repo
RUN yum clean all

# 2. Install zookeeper and kafka
RUN yum install -y java-11-openjdk
RUN yum install -y confluent-kafka-2.12
RUN yum install -y confluent-security

# 2. Configure Kafka
COPY server.properties /etc/kafka/server.properties

# 3. Copy the prometheus and jmx-exporter configs.
COPY prometheus/kafka.yml /etc/prometheus-config/kafka.yml
COPY jmx-reporter/jmx_prometheus_javaagent-0.3.1.jar /jmx-reporter/jmx_prometheus_javaagent.jar

EXPOSE 9092

CMD kafka-server-start /etc/kafka/server.properties

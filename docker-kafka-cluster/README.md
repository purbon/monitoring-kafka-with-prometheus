# A dummy dockerized kafka cluster

In a local environment, mostly with the objective to show and tell the
prometheus setup, you could:

In case of need, start the local Apache Kafka cluster. For this, from the docker-kafka-cluster director,

```bash
cd docker-kafka-cluster
```
you can start the cluster:

```bash
docker-compose up -d

```

Listing the current running containers should show you something like this:

```bash
➜  docker-kafka-cluster git:(master) ✗ docker ps
CONTAINER ID        IMAGE                                    COMMAND                  CREATED             STATUS              PORTS                                        NAMES
ef35e46b12d4        confluentinc/cp-enterprise-kafka:5.1.0   "/etc/confluent/dock…"   36 minutes ago      Up 36 minutes       9092/tcp, 0.0.0.0:9093->9093/tcp             docker-kafka-cluster_kafka3_1
d602784c8400        confluentinc/cp-enterprise-kafka:5.1.0   "/etc/confluent/dock…"   36 minutes ago      Up 36 minutes       0.0.0.0:9092->9092/tcp                       docker-kafka-cluster_kafka2_1
742a69670714        confluentinc/cp-enterprise-kafka:5.1.0   "/etc/confluent/dock…"   36 minutes ago      Up 36 minutes       0.0.0.0:9091->9091/tcp, 9092/tcp             docker-kafka-cluster_kafka1_1
eed59a7c71b8        confluentinc/cp-enterprise-kafka:5.1.0   "/etc/confluent/dock…"   36 minutes ago      Up 36 minutes       9092/tcp, 0.0.0.0:9094->9094/tcp             docker-kafka-cluster_kafka4_1
2daffe3c3232        confluentinc/cp-zookeeper:5.1.0          "/etc/confluent/dock…"   36 minutes ago      Up 36 minutes       2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp   docker-kafka-cluster_zookeeper_1
```
this means your 4 brokers and single zookeeper are up and running for
your monitoring setup creation to start.

#!/usr/bin/env bash

PAYLOAD=`KAFKA_OPTS="" zookeeper-shell zookeeper get /controller  2>&1 | head -n 6 | tail -n 1`
#curl -X PUT -H "Content-Type: application/json" --data foo.json http://elasticsearch:9200/kafka-controller/_doc/1


# Create the two indices
curl -X PUT "elasticsearch:9200/kafka-controller?pretty"
curl -X PUT "elasticsearch:9200/kafka-controller/_mapping/_doc?pretty" -H 'Content-Type: application/json' -d'
{
  "properties": {
    "version": {
      "type": "long"
    },
    "brokerid": {
      "type": "keyword"
    },
    "timestamp": {
      "type": "date"
    }
  }
}
'

curl -X PUT "elasticsearch:9200/kafka-controller/_doc/1?pretty" -H 'Content-Type: application/json' -d $PAYLOAD
#rm -f foo.json

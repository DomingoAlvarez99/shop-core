#!/bin/bash

topic=$1

docker exec \
  -e TOPIC="$topic" \
  kafka \
  bash \
  -c 'kafka-topics \
        --delete \
        --topic "$TOPIC" \
        --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'

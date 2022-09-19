# Apache Kafka With spring boot

This Sample project demonstrates the proceedures to set up apache Kafka(an open source distributed event streaming platform)
Kafka depends on ZooKeeper which is used to track the status of nodes inn the Kafka cluster and mentain the list of Kafka topics and messages.

## Installing Apache Kafka with zookeeper and kafdrop - using docker-compose

```yaml
version: '3.5'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - 2181:2181

  kafka:
    image: wurstmeister/kafka
    ports:
      - 9092:9092
    environment:
      KAFKA_LISTENERS: "INTERNAL://:29092,EXTERNAL://:9092"
      KAFKA_ADVERTISED_LISTENERS: "INTERNAL://kafka:29092,EXTERNAL://localhost:9092"
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: "INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT"
      KAFKA_INTER_BROKER_LISTENER_NAME: "INTERNAL"
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    depends_on:
      - zookeeper

  kafdrop:
    image: obsidiandynamics/kafdrop
    restart: "no"
    ports:
      - 9000:9000
    environment:
      KAFKA_BROKERCONNECT: kafka:29092
      JVM_OPTS: "-Xms16M -Xmx48M -Xss180K -XX:-TieredCompilation -XX:+UseStringDeduplication -noverify"
    depends_on:
      - kafka
```

### To run our setup:

```docker-compose -up -d```

### To access your Kafka application through the browser
  http://localhost:9000/

With the above steps, running the springboot application will create a kafka topic with name kakavi and 
will produce and consume messages from that topic


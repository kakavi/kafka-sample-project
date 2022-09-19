package com.kakavi.kafka.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    @Value('${spring.kafka.bootstrap-servers}')
    private String bootstrapServers

    Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        return props
    }

    //params are the type of data you want to send
    @Bean
    ProducerFactory<String,String> producerFactory() {
        return new DefaultKafkaProducerFactory<String, String>(producerConfig())
    }

    //used for sending data
    @Bean
    KafkaTemplate<String, String> kafkaTemplate(
            ProducerFactory<String,String> producerFactory
    ) {
        return new KafkaTemplate<String, String>(producerFactory)
    }
}

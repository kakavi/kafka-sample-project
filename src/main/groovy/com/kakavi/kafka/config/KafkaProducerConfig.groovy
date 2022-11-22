package com.kakavi.kafka.config

import com.xenotech.commons.messages.SessionMessage
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {

    @Value('${spring.kafka.bootstrap-servers}')
    private String bootstrapServers

    Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
        return props
    }

    @Bean
    ProducerFactory<String, SessionMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<String, SessionMessage>(producerConfig())
    }

    //used for sending data
    @Bean
    KafkaTemplate<String, SessionMessage> kafkaTemplate(
            ProducerFactory<String,SessionMessage> producerFactory
    ) {
        return new KafkaTemplate<String, SessionMessage>(producerFactory)
    }
}

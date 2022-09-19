package com.kakavi.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer

@Configuration
class KafkaConsumerConfig {

    @Value('${spring.kafka.bootstrap-servers}')
    private String bootstrapServers

    Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class)
        return props
    }

    @Bean
    ConsumerFactory<String,String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerConfig())
    }

    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory (
            ConsumerFactory<String,String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>()
        factory.setConsumerFactory(consumerFactory)
    }
}

package com.kakavi.kafka.config

import com.kakavi.kafka.model.Student
import com.xenotech.commons.messages.SessionMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConsumerConfig {

    @Value('${spring.kafka.bootstrap-servers}')
    private String bootstrapServers

    Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class)
        props.put(JsonDeserializer.TYPE_MAPPINGS,
                "session_message:com.xenotech.commons.messages.SessionMessage,student:com.kakavi.kafka.model.Student")
        return props
    }

    @Bean
    ConsumerFactory<String,SessionMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, SessionMessage>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(SessionMessage.class)
        )
    }


    @Bean
    ConcurrentKafkaListenerContainerFactory<String, SessionMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SessionMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>()
        factory.setConsumerFactory(consumerFactory())
        return factory
    }

    @Bean
    RecordMessageConverter multiTypeConverter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter()
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper()
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID)
        typeMapper.addTrustedPackages("*")

        Map<String, Class<?>> mappings = new HashMap<>()
        mappings.put("session_message", SessionMessage.class)
        mappings.put("student", Student.class)

        typeMapper.setIdClassMapping(mappings)
        converter.setTypeMapper(typeMapper)
        return converter
    }

    @Bean
    ConsumerFactory<String, Object> multiTypeConsumerFactory() {
        return new DefaultKafkaConsumerFactory<String, Object>(consumerConfig())
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>()
        factory.setConsumerFactory(multiTypeConsumerFactory())
        factory.setMessageConverter(multiTypeConverter())
        return factory
    }
}

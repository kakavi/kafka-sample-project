package com.kakavi.kafka.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {

    @Bean
    NewTopic kakavisTopic() {
        return TopicBuilder.name("multi-type-topic")
        .build()
    }
}

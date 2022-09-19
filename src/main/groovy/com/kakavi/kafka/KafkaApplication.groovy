package com.kakavi.kafka

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class KafkaApplication {

    static void main(String[] args) {
        SpringApplication.run(KafkaApplication, args)
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate) {
        return (args) -> {
            kafkaTemplate.send("kakavi", "hello kafka")
        }
    }

}

package com.kakavi.kafka

import com.xenotech.commons.messages.SessionMessage
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
    CommandLineRunner commandLineRunner(KafkaTemplate<String, SessionMessage> kafkaTemplate) {
        return (args) -> {
            SessionMessage messsage = SessionMessage.builder()
            .username("testkafka")
            .authId("kafkatesti9")
            .telephone("77777505033")
            .firstName("kafkatest")
            .lastName("kafkatest")
            .email("kafkatest")
            .dateLoggedIn(new Date())
            .build()
            kafkaTemplate.send("kakavi", messsage)
        }
    }

}

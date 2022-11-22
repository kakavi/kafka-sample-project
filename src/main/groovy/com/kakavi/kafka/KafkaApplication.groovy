package com.kakavi.kafka

import com.kakavi.kafka.model.Student
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
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
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
            multiTypeKafkaTemplate.send("multi-type-topic", messsage)

            Student student = Student.builder()
            .firstName("kakama")
            .lastName("victor")
            .age(37)
            .payedAmount(new BigDecimal("45000000"))
            .build()
            multiTypeKafkaTemplate.send("multi-type-topic", student)
        }
    }

}

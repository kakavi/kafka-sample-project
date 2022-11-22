package com.kakavi.kafka.listeners

import com.kakavi.kafka.model.Student
import com.xenotech.commons.messages.SessionMessage
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(id = "multiTypeGroup", topics = "multi-type-topic")
class MultiTypeKafkaListener {

    @KafkaHandler
    void handleSessionMessage(SessionMessage message) {
        println("===================================Listener received: ${message.getEmail()}")
    }

    @KafkaHandler
    void handleStudent(Student student) {
        println("===================================Listener received: ${student.toString()}")
    }

    @KafkaHandler(isDefault = true)
    void unknown(Object object) {
        println("===================================Kafka recieved unknown object: ${object}")
    }
}

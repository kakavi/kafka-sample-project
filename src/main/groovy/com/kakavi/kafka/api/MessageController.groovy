package com.kakavi.kafka.api

import com.xenotech.commons.messages.SessionMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class MessageController {

    private KafkaTemplate<String, SessionMessage> kafkaTemplate

    MessageController(KafkaTemplate<String, SessionMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate
    }

    @PostMapping("")
    void publish(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("kakavi", messageRequest.message)
    }
}

package com.kakavi.kafka.api

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate

    MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate
    }

    @PostMapping("")
    void publish(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("kakavi", messageRequest.message)
    }
}

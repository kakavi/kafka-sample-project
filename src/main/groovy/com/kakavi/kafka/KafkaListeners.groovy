package com.kakavi.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListeners {

    @KafkaListener(
            topics = "kakavi",
            groupId = "kakavigroup"
    )
    void listeners(String data) {
        println("Listener received: ${data}")
    }
}

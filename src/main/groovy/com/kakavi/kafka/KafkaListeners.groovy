package com.kakavi.kafka

import com.xenotech.commons.messages.SessionMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListeners {

    @KafkaListener(
            topics = "kakavi",
            groupId = "testers"
    )
    void listeners(SessionMessage data) {
        println("===================================Listener received: ${data.getEmail()}")
    }
}

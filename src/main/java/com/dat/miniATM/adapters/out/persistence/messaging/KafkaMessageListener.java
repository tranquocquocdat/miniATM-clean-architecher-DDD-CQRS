package com.dat.miniATM.adapters.out.persistence.messaging;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@KafkaListener(topics = "topic_name", groupId = "group_id")
@Service
public class KafkaMessageListener {

    @KafkaHandler
    public void handle(String message) {
        // Handle the message
        System.out.println("Received message: " + message);
    }
}

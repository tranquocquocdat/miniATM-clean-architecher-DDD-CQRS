package com.dat.miniATM.adapters.out.persistence.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "topic_name", groupId = "group_id")
    public void listen(String message) {
        // Handle the message
        System.out.println("Received message: " + message);
    }
}

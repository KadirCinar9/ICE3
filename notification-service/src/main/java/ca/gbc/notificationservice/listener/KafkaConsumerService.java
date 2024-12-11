package ca.gbc.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }
}

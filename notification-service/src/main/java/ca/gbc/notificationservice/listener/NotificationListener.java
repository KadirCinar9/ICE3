package ca.gbc.notificationservice.listener;

import ca.gbc.notificationservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void listenOrderEvents(String message) {
        System.out.println("Received Kafka message: " + message);

        // E-posta gönderimi
        String emailBody = "New order received: " + message;
        emailService.sendEmail("test@inbox.mailtrap.io", "Order Notification", emailBody);
    }
}

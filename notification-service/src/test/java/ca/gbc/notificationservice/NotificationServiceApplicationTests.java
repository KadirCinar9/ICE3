package ca.gbc.notificationservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"order-events"}, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class NotificationServiceApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void contextLoads() {
        // Example test to ensure the context loads properly
    }
}

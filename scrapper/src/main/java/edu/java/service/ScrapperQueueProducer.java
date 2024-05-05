package edu.java.service;

import edu.java.api.request.LinkUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScrapperQueueProducer {

    private final KafkaTemplate<String, LinkUpdate> kafkaTemplate;

    @Value("link-update")
    private String topicName;

    public ScrapperQueueProducer(KafkaTemplate<String, LinkUpdate> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LinkUpdate update) {
        this.kafkaTemplate.send(topicName, update);
    }
}

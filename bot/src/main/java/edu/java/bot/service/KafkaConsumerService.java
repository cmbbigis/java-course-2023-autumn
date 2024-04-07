package edu.java.bot.service;

import edu.java.bot.api.request.LinkUpdate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final NotificationService notificationService;

    public KafkaConsumerService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RetryableTopic(attempts = "1",
                    dltStrategy = DltStrategy.ALWAYS_RETRY_ON_ERROR,
                    concurrency = "1",
                    autoCreateTopics = "false",
                    dltTopicSuffix = "-dlq")
    @KafkaListener(topics = "${app.scrapper-topic.name}", groupId = "${app.kafka.group-id}")
    public void listen(LinkUpdate update) {
        notificationService.handleNotification(update);
    }
}

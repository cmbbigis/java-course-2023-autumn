package edu.java.service;

import edu.java.api.request.LinkUpdate;
import edu.java.client.BotClient;
import edu.java.configuration.ApplicationConfig;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final ScrapperQueueProducer queueProducer;
    private final BotClient botClient;
    private final ApplicationConfig config;

    public NotificationService(ScrapperQueueProducer queueProducer, BotClient botClient, ApplicationConfig config) {
        this.queueProducer = queueProducer;
        this.botClient = botClient;
        this.config = config;
    }

    public void sendNotification(LinkUpdate update) {
        if (config.useQueue()) {
            queueProducer.send(update);
        } else {
            botClient.postUpdate(update).subscribe();
        }
    }
}

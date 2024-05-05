package edu.java.configuration;

import edu.java.service.LinkService;
import edu.java.service.LinkUpdater;
import edu.java.service.TgChatService;
import edu.java.service.jpa.JpaLinkService;
import edu.java.service.jpa.JpaLinkUpdater;
import edu.java.service.jpa.JpaTgChatService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    public LinkService linkService() {
        return new JpaLinkService();
    }

    @Bean LinkUpdater linkUpdater() {
        return new JpaLinkUpdater();
    }

    @Bean TgChatService tgChatService() {
        return new JpaTgChatService();
    }
}

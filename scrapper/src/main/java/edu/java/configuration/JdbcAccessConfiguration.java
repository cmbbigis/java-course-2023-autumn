package edu.java.configuration;

import edu.java.service.LinkService;
import edu.java.service.LinkUpdater;
import edu.java.service.TgChatService;
import edu.java.service.jdbc.JdbcLinkService;
import edu.java.service.jdbc.JdbcLinkUpdater;
import edu.java.service.jdbc.JdbcTgChatService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    public LinkService linkService() {
        return new JdbcLinkService();
    }

    @Bean LinkUpdater linkUpdater() {
        return new JdbcLinkUpdater();
    }

    @Bean TgChatService tgChatService() {
        return new JdbcTgChatService();
    }
}

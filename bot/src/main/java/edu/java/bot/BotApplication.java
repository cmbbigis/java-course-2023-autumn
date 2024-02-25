package edu.java.bot;

import edu.java.bot.body.TelegramBot;
import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

    private final TelegramBot telegramBot;

    @Autowired
    public BotApplication(ApplicationConfig config) {
        this.telegramBot = new TelegramBot(new com.pengrad.telegrambot.TelegramBot(config.getTelegramToken()));
    }

    @Bean
    public TelegramBot telegramBot(ApplicationConfig config) {
        return new TelegramBot(new com.pengrad.telegrambot.TelegramBot(config.getTelegramToken()));
    }

    @EventListener(ContextRefreshedEvent.class)
    public void startup() {
        telegramBot.start();
    }
}

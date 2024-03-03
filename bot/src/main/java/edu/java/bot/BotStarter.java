package edu.java.bot;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.telegram.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BotStarter {
    private final TelegramBot telegramBot;

    @Autowired
    public BotStarter(ApplicationConfig config) {
        this.telegramBot = new TelegramBot(new com.pengrad.telegrambot.TelegramBot(config.getTelegramToken()));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        telegramBot.start();
    }
}

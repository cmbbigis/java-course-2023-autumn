package edu.java.bot.service;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.request.LinkUpdate;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.telegram.TelegramBot;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UpdateHandler {
    private final TelegramBot bot;

    public UpdateHandler(ApplicationConfig config) {
        this.bot = new TelegramBot(new com.pengrad.telegrambot.TelegramBot(config.getTelegramToken()));
    }

    public void handleUpdate(LinkUpdate update) {
        List<SendMessage> requests = new ArrayList<>();

        for (Long chatID : update.getTgChatIds()) {
            String message = String.format(
                "Ссылка: %s\nОбновление: %s",
                update.getUrl(),
                update.getDescription()
            );
        }

        for (SendMessage request : requests) {
            bot.execute(request);
        }
    }
}

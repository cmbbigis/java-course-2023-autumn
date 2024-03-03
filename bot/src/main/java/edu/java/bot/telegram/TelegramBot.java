package edu.java.bot.telegram;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.service.TelegramMessageProcessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

public class TelegramBot implements Bot {
    private final com.pengrad.telegrambot.TelegramBot bot;
    @Getter private static Map<String, Boolean> links = new HashMap<>();
    private final TelegramMessageProcessor messageProcessor;

    public TelegramBot(com.pengrad.telegrambot.TelegramBot bot) {
        this.bot = bot;
        this.messageProcessor = new TelegramMessageProcessor();

    }

    @Bean
    public TelegramBot telegramBot(ApplicationConfig config) {
        return new TelegramBot(new com.pengrad.telegrambot.TelegramBot(config.getTelegramToken()));
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        bot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            execute(messageProcessor.process(update));
        }
        return CONFIRMED_UPDATES_ALL;
    }

    public void start() {
        bot.setUpdatesListener(this);
    }

    @Override
    public void close() {
        bot.shutdown();
    }
}

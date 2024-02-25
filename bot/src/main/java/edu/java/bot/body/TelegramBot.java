package edu.java.bot.body;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.messageServices.TelegramMessageProcessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;


public class TelegramBot implements Bot {
    private final com.pengrad.telegrambot.TelegramBot bot;
    @Getter private static Map<String, Boolean> links = new HashMap<>();
    private final TelegramMessageProcessor messageProcessor;

    public TelegramBot(com.pengrad.telegrambot.TelegramBot bot) {
        this.bot = bot;
        this.messageProcessor = new TelegramMessageProcessor();

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

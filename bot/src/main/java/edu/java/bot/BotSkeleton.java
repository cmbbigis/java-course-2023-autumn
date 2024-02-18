package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;


public class BotSkeleton {
    //test
    //another one test
    private final TelegramBot bot;
    @Getter private Map<String, Boolean> links;

    public BotSkeleton(TelegramBot bot) {
        this.bot = bot;
        this.links = new HashMap<>();
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                handleUpdate(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void sendMessage(long chatId, String text) {
        bot.execute(new SendMessage(chatId, text));
    }

    public void handleUpdate(Update update) {
        long chatId = update.message().chat().id();
        String noLinkMessage = "В сообщении отсутствует ссылка";
        if (update.message().text().equals("/start")) {
            sendMessage(chatId, "Привет, теперь Вы зарегистированы!");
        } else if (update.message().text().equals("/help")) {
            sendMessage(chatId,
                    """
                            Список команд:
                            /start -- зарегистрировать пользователя
                            /help -- вывести окно с командами
                            /track -- начать отслеживание ссылки
                            /untrack -- прекратить отслеживание ссылки
                            /list -- показать список отслеживаемых ссылок""");
        } else if (update.message().text().equals("/list")) {
            if (links.keySet().isEmpty()) {
                sendMessage(chatId, "Список пуст");
            } else {
                sendMessage(chatId, links.keySet().toString());
            }
        } else if (update.message().text().startsWith("/track")) {
            var splitedMessage = update.message().text().split(" ");
            if (splitedMessage.length < 2) {
                sendMessage(chatId, noLinkMessage);
            } else {
                String link = update.message().text().split(" ")[1];
                links.put(link, true);
                sendMessage(chatId, String.format("Ссылка %s теперь отслеживается", link));
            }
        } else if (update.message().text().startsWith("/untrack")) {
            var splitedMessage = update.message().text().split(" ");
            if (splitedMessage.length < 2) {
                sendMessage(chatId, noLinkMessage);
            } else {
                String link = update.message().text().split(" ")[1];
                links.remove(link);
                sendMessage(chatId, String.format("Ссылка %s больше не отслеживается", link));
            }
        }  else {
            sendMessage(chatId, "Я не знаю такой команды :(");
        }
    }
}

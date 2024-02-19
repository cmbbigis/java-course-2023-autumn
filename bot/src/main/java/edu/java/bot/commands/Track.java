package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.body.TelegramBot;

public class Track implements Command {
    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Начать отслеживание ссылки";
    }

    @Override
    public SendMessage handle(Update update) {
        var splitedMessage = update.message().text().split(" ");
        if (splitedMessage.length < 2) {
            return new SendMessage(update.message().chat().id(), "В сообщении отсутствует ссылка");
        } else {
            String link = update.message().text().split(" ")[1];
            TelegramBot.getLinks().put(link, true);
            return new SendMessage(update.message().chat().id(), String.format("Ссылка %s теперь отслеживается", link));
        }
    }
}

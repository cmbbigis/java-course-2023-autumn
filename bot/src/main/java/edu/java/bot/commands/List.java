package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.body.TelegramBot;

public class List implements Command {
    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
       return "Список отслеживаемых ссылок";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), TelegramBot.getLinks().keySet().isEmpty()
            ? "Список пуст" : TelegramBot.getLinks().keySet().toString());
    }
}

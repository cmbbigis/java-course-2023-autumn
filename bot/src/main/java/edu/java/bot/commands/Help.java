package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Help implements Command {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Список поддерживаемых команд";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), """
                  Список команд:
                    /start -- зарегистрировать пользователя
                    /help -- вывести окно с командами
                    /track -- начать отслеживание ссылки
                    /untrack -- прекратить отслеживание ссылки
                    /list -- показать список отслеживаемых ссылок""");
    }
}

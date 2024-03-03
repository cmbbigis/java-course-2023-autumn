package edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Start implements Command {
    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Начать общение с ботом";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Привет! Теперь Вы зарегистированы!");
    }
}

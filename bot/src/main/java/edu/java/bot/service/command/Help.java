package edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.TelegramMessageProcessor;

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
        var sb = new StringBuilder("Список команд:\n");
        for (var command : TelegramMessageProcessor.COMMANDS) {
            sb.append("\t").append(command.command()).append(" -- ").append(command.description()).append("\n");
        }
        return new SendMessage(update.message().chat().id(), sb.toString());
    }
}

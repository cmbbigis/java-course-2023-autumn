package edu.java.bot.messageServices;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramMessageProcessor implements UserMessageProcessor {
    private static final List<? extends Command> COMMANDS = new ArrayList<>(
        Arrays.asList(
            new edu.java.bot.commands.Start(),
            new edu.java.bot.commands.List(),
            new edu.java.bot.commands.Track(),
            new edu.java.bot.commands.Untrack(),
            new edu.java.bot.commands.Help()));

    @Override
    public List<? extends Command> commands() {
        return COMMANDS;
    }

    @Override
    public SendMessage process(Update update) {
        for (Command command : COMMANDS) {
            if (command.supports(update)) {
                return command.handle(update);
            }
        }
        return new edu.java.bot.commands.UnknownCommand().handle(update);
    }
}

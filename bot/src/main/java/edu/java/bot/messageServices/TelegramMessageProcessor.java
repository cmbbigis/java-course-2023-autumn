package edu.java.bot.messageServices;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.Untrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramMessageProcessor implements UserMessageProcessor {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(new Start().command()),
        new KeyboardButton(new Help().command()),
        new KeyboardButton(new edu.java.bot.commands.List().command()),
        new KeyboardButton(new Track().command()),
        new KeyboardButton(new Untrack().command()));

    private static final List<? extends Command> COMMANDS = new ArrayList<>(
        Arrays.asList(
            new edu.java.bot.commands.Start(),
            new edu.java.bot.commands.List(),
            new edu.java.bot.commands.Track(),
            new edu.java.bot.commands.Untrack(),
            new edu.java.bot.commands.Help()));

    public TelegramMessageProcessor() {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
    }

    @Override
    public List<? extends Command> commands() {
        return COMMANDS;
    }

    @Override
    public SendMessage process(Update update) {
        for (Command command : COMMANDS) {
            if (command.supports(update)) {
                return command.handle(update).replyMarkup(replyKeyboardMarkup);
            }
        }
        return new edu.java.bot.commands.UnknownCommand().handle(update).replyMarkup(replyKeyboardMarkup);
    }
}

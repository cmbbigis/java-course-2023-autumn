package edu.java.bot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.command.Command;
import edu.java.bot.service.command.Help;
import edu.java.bot.service.command.Start;
import edu.java.bot.service.command.Track;
import edu.java.bot.service.command.Untrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramMessageProcessor implements UserMessageProcessor {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(new Start().command()),
        new KeyboardButton(new Help().command()),
        new KeyboardButton(new edu.java.bot.service.command.List().command()),
        new KeyboardButton(new Track().command()),
        new KeyboardButton(new Untrack().command()));

    public static final List<? extends Command> COMMANDS = new ArrayList<>(
        Arrays.asList(
            new Start(),
            new edu.java.bot.service.command.List(),
            new Track(),
            new Untrack(),
            new Help()));

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
        return new SendMessage(update.message().chat().id(), "Неизвестная команда").replyMarkup(replyKeyboardMarkup);
    }
}

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
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
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

    private final Counter counter;

    public TelegramMessageProcessor() {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        this.counter = Metrics.counter("proceed.messages");
    }

    @Override
    public List<? extends Command> commands() {
        return COMMANDS;
    }

    @Override
    public SendMessage process(Update update) {
        for (Command command : COMMANDS) {
            if (command.supports(update)) {
                counter.increment();
                return command.handle(update).replyMarkup(replyKeyboardMarkup);
            }
        }
        counter.increment();
        return new SendMessage(update.message().chat().id(), "Неизвестная команда").replyMarkup(replyKeyboardMarkup);
    }
}

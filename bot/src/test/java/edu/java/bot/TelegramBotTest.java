package edu.java.bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.TelegramMessageProcessor;
import edu.java.bot.telegram.TelegramBot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class TelegramBotTest {
    private TelegramBot telegramBot;
    private com.pengrad.telegrambot.TelegramBot bot;

    @BeforeEach
    public void setup() {
        bot = mock(com.pengrad.telegrambot.TelegramBot.class);
        telegramBot = new TelegramBot(bot);
        TelegramBot.getLinks().clear();
    }

    @Test
    public void testStart() {
        telegramBot.start();

        verify(bot).setUpdatesListener(any(UpdatesListener.class));
    }

    @Test
    public void startCommandHandleUpdate() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);


        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/start");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Привет! Теперь Вы зарегистированы!")
        ));
    }

    @Test
    public void helpCommandHandleUpdate() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/help");

        telegramBot.process(new ArrayList<>(List.of(update)));

        var sb = new StringBuilder("Список команд:\n");
        for (var command : TelegramMessageProcessor.COMMANDS) {
            sb.append("\t").append(command.command()).append(" -- ").append(command.description()).append("\n");
        }

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().contentEquals(sb)
        ));
    }

    @Test
    public void listCommandWithEmptyListHandleUpdate() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/list");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Список пуст")
        ));
    }

    @Test
    public void listCommandWithNotEmptyListHandleUpdate() {
        TelegramBot.getLinks().put("example.com", true);

        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/list");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().contains("example.com")
        ));
    }

    @Test
    public void trackCommandHandleUpdate() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/track example.com");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters()
                .get("text")
                .toString()
                .equals("Ссылка example.com теперь отслеживается")
        ));

        Assertions.assertTrue(TelegramBot.getLinks().containsKey("example.com"));
    }

    @Test
    public void untrackCommandHandleUpdate() {
        TelegramBot.getLinks().put("example.com", true);

        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/untrack example.com");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Ссылка example.com больше не отслеживается")
        ));

        Assertions.assertTrue(TelegramBot.getLinks().isEmpty());
    }

    @Test
    public void unknownCommandHandleUpdate() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/hleb");

        telegramBot.process(new ArrayList<>(List.of(update)));

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Неизвестная команда")
        ));
    }
}

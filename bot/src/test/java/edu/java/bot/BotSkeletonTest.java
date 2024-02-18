package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BotSkeletonTest {
    private BotSkeleton botSkeleton;
    private TelegramBot bot;

    @BeforeEach
    public void setup() {
        bot = mock(TelegramBot.class);
        botSkeleton = new BotSkeleton(bot);
    }

    @Test
    public void testStart() {
        botSkeleton.start();

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

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
                sendMessage.getParameters().get("text").toString().equals("Привет, теперь Вы зарегистированы!")
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

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("""
                    Список команд:
                    /start -- зарегистрировать пользователя
                    /help -- вывести окно с командами
                    /track -- начать отслеживание ссылки
                    /untrack -- прекратить отслеживание ссылки
                    /list -- показать список отслеживаемых ссылок""")
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

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Список пуст")
        ));
    }

    @Test
    public void listCommandWithNotEmptyListHandleUpdate() {
        botSkeleton.getLinks().put("example.com", true);

        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/list");

        botSkeleton.handleUpdate(update);

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

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters()
                .get("text")
                .toString()
                .equals("Ссылка example.com теперь отслеживается")
        ));

        Assertions.assertTrue(botSkeleton.getLinks().containsKey("example.com"));
    }

    @Test
    public void untrackCommandHandleUpdate() {
        botSkeleton.getLinks().put("example.com", true);

        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.text()).thenReturn("/untrack example.com");

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Ссылка example.com больше не отслеживается")
        ));

        Assertions.assertTrue(botSkeleton.getLinks().isEmpty());
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

        botSkeleton.handleUpdate(update);

        verify(bot).execute(argThat((SendMessage sendMessage) ->
            sendMessage.getParameters().get("text").toString().equals("Я не знаю такой команды :(")
        ));
    }
}

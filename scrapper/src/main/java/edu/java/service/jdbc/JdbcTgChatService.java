package edu.java.service.jdbc;

import edu.java.domain.ChatDao;
import edu.java.domain.entity.Chat;
import edu.java.service.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcTgChatService implements TgChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    public void register(long tgChatId) {
        Chat chat = new Chat();
        chat.setId(tgChatId);
        chat.setName(String.valueOf(chat.getId()));
        chatDao.add(chat);
    }

    @Override
    public void unregister(long tgChatId) {
        chatDao.remove(tgChatId);
    }
}

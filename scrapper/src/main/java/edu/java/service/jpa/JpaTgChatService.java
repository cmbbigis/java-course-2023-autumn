package edu.java.service.jpa;

import edu.java.domain.entity.Chat;
import edu.java.domain.jpa.ChatRepository;
import edu.java.service.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaTgChatService implements TgChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void register(long tgChatId) {
        Chat chat = new Chat();
        chat.setId(tgChatId);
        chatRepository.save(chat);
    }

    @Override
    public void unregister(long tgChatId) {
        chatRepository.deleteById(tgChatId);
    }
}

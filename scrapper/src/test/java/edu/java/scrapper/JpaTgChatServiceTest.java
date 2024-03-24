package edu.java.scrapper;

import edu.java.domain.jpa.ChatRepository;
import edu.java.service.jpa.JpaTgChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class JpaTgChatServiceTest extends IntegrationTest {
    @Autowired
    private JpaTgChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void testRegisterChat() {
        long chatId = 1L;
        chatService.register(chatId);
        assertNotNull(chatRepository.findById(chatId).orElse(null));
    }

    // Добавьте здесь другие тесты для JpaTgChatService
}

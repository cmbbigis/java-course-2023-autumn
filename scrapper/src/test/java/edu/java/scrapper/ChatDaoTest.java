package edu.java.scrapper;

import edu.java.domain.jdbc.ChatDao;
import edu.java.domain.entity.Chat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChatDaoTest extends IntegrationTest {
    @MockBean
    private ChatDao chatDao;

    @Test
    @Transactional
    @Rollback
    void addTest()   {
        Chat chat = new Chat(1L,
            "Example",
            LocalDateTime.of(2003, 10, 24, 23, 23, 23),
            "cmbbigis");
        chatDao.add(chat);
        when(chatDao.findAll()).thenReturn(Arrays.asList(chat));
        List<Chat> entities = chatDao.findAll();
        Assertions.assertTrue(entities.contains(chat));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest()   {
        Chat chat = new Chat(1L,
            "Example",
            LocalDateTime.of(2003, 10, 24, 23, 23, 23),
            "cmbbigis");
        chatDao.add(chat);
        chatDao.remove(1L);
        when(chatDao.findAll()).thenReturn(Arrays.asList());
        List<Chat> entities = chatDao.findAll();
        assertFalse(entities.contains(chat));
    }
}

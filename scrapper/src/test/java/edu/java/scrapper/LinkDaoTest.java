package edu.java.scrapper;

import edu.java.domain.jdbc.LinkDao;
import edu.java.domain.entity.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@SpringBootTest
public class LinkDaoTest extends IntegrationTest {
    @Autowired
    private LinkDao linkDao;

    @Test
    @Transactional
    @Rollback
    void addTest()   {
        Link link = new Link(1L,
            "www.example.com",
            LocalDateTime.of(2003, 10, 24, 23, 23, 23),
            "cmbbigis");
        linkDao.add(link);
        List<Link> entities = linkDao.findAll();
        Assertions.assertTrue(entities.contains(link));
    }

    @Test
    @Transactional
    @Rollback
    void removeTest()   {
        Link link = new Link(1L,
            "www.example.com",
            LocalDateTime.of(2003, 10, 24, 23, 23, 23),
            "cmbbigis");
        linkDao.add(link);
        linkDao.remove(1L);
        List<Link> entities = linkDao.findAll();
        Assertions.assertFalse(entities.contains(link));
    }
}

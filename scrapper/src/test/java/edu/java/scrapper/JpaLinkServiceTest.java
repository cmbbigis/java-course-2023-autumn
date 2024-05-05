package edu.java.scrapper;

import edu.java.domain.entity.Link;
import edu.java.domain.jpa.LinkRepository;
import edu.java.service.jpa.JpaLinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class JpaLinkServiceTest extends IntegrationTest {
    @Autowired
    private JpaLinkService linkService;

    @Autowired
    private LinkRepository linkRepository;

    @Test
    public void testAddLink() {
        long chatId = 1L;
        URI url = URI.create("http://example.com");
        Link link = linkService.add(chatId, url);
        assertNotNull(link);
        assertEquals(url, URI.create(link.getUrl()));
        assertNotNull(linkRepository.findById(link.getId()));
    }
}

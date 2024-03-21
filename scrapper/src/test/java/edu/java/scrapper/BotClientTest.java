package edu.java.scrapper;

import edu.java.api.BotClient;
import edu.java.api.request.LinkUpdate;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class BotClientTest {

    @Test
    public void testPostUpdate() throws URISyntaxException {
        BotClient client = new BotClient("http://localhost:8090");

        LinkUpdate update = new LinkUpdate();
        update.setId(1);
        update.setUrl(new URI("http://example.com"));
        update.setDescription("test");
        update.setTgChatIds(Arrays.asList(1L, 2L, 3L));

        Mono<LinkUpdate> result = client.postUpdate(update);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }
}

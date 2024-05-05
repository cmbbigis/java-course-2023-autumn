package edu.java.bot;

import edu.java.bot.api.ScrapperClient;
import edu.java.bot.api.request.LinkUpdate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ScrapperClientTest {

    @Test
    public void testPostUpdate() throws URISyntaxException {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);

        LinkUpdate update = new LinkUpdate();
        update.setId(1);
        update.setUrl(new URI("http://example.com"));
        update.setDescription("test");
        update.setTgChatIds(Arrays.asList(1L, 2L, 3L));

        Mockito.when(client.postUpdate(update)).thenReturn(Mono.just(update));

        Mono<LinkUpdate> result = client.postUpdate(update);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }
}
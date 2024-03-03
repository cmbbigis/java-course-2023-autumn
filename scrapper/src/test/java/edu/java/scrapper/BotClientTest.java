package edu.java.scrapper;

import edu.java.api.BotClient;
import edu.java.api.request.AddLinkRequest;
import edu.java.api.request.RemoveLinkRequest;
import edu.java.api.response.LinkResponse;
import edu.java.api.response.ListLinksResponse;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BotClientTest {

    @Test
    public void testRegisterChat() {
        BotClient client = new BotClient("http://localhost:8080");

        Mono<Void> result = client.registerChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testDeleteChat() {
        BotClient client = new BotClient("http://localhost:8080");

        Mono<Void> result = client.deleteChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testGetAllLinks() {
        BotClient client = new BotClient("http://localhost:8080");

        Mono<ListLinksResponse> result = client.getAllLinks(12345L);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getLinks().size() == 1)
            .verifyComplete();
    }

    @Test
    public void testAddLink() {
        BotClient client = new BotClient("http://localhost:8080");

        AddLinkRequest request = new AddLinkRequest();
        request.setLink("http://example.com");

        Mono<LinkResponse> result = client.addLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }

    @Test
    public void testRemoveLink() {
        BotClient client = new BotClient("http://localhost:8080");

        RemoveLinkRequest request = new RemoveLinkRequest();
        request.setLink("http://example.com");

        Mono<LinkResponse> result = client.removeLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }
}

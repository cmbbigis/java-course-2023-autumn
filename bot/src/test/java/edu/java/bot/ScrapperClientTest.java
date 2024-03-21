package edu.java.bot;

import edu.java.bot.api.ScrapperClient;
import edu.java.bot.api.request.AddLinkRequest;
import edu.java.bot.api.request.RemoveLinkRequest;
import edu.java.bot.api.response.LinkResponse;
import edu.java.bot.api.response.ListLinksResponse;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ScrapperClientTest {

    @Test
    public void testRegisterChat() {
        ScrapperClient client = new ScrapperClient("http://localhost:8080");

        Mono<Void> result = client.registerChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testDeleteChat() {
        ScrapperClient client = new ScrapperClient("http://localhost:8080");

        Mono<Void> result = client.deleteChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testGetAllLinks() {
        ScrapperClient client = new ScrapperClient("http://localhost:8080");

        Mono<ListLinksResponse> result = client.getAllLinks(12345L);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getLinks().size() == 1)
            .verifyComplete();
    }

    @Test
    public void testAddLink() {
        ScrapperClient client = new ScrapperClient("http://localhost:8080");

        AddLinkRequest request = new AddLinkRequest();
        request.setLink("http://example.com");

        Mono<LinkResponse> result = client.addLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }

    @Test
    public void testRemoveLink() {
        ScrapperClient client = new ScrapperClient("http://localhost:8080");

        RemoveLinkRequest request = new RemoveLinkRequest();
        request.setLink("http://example.com");

        Mono<LinkResponse> result = client.removeLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }
}

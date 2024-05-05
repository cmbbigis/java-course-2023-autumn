package edu.java.bot;

import edu.java.bot.api.ScrapperClient;
import edu.java.bot.api.request.AddLinkRequest;
import edu.java.bot.api.request.RemoveLinkRequest;
import edu.java.bot.api.response.LinkResponse;
import edu.java.bot.api.response.ListLinksResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Collections;

public class ScrapperClientTest {
    @Test
    public void testRegisterChat() {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);
        Mockito.when(client.registerChat(12345L)).thenReturn(Mono.empty());

        Mono<Void> result = client.registerChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testDeleteChat() {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);
        Mockito.when(client.deleteChat(12345L)).thenReturn(Mono.empty());

        Mono<Void> result = client.deleteChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testGetAllLinks() {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);
        ListLinksResponse mockResponse = new ListLinksResponse();
        mockResponse.setLinks(Collections.singletonList(new LinkResponse()));
        Mockito.when(client.getAllLinks(12345L)).thenReturn(Mono.just(mockResponse));

        Mono<ListLinksResponse> result = client.getAllLinks(12345L);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getLinks().size() == 1)
            .verifyComplete();
    }

    @Test
    public void testAddLink() {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);
        AddLinkRequest request = new AddLinkRequest();
        request.setLink("http://example.com");
        LinkResponse mockResponse = new LinkResponse();
        mockResponse.setId(1);
        Mockito.when(client.addLink(12345L, request)).thenReturn(Mono.just(mockResponse));

        Mono<LinkResponse> result = client.addLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }

    @Test
    public void testRemoveLink() {
        ScrapperClient client = Mockito.mock(ScrapperClient.class);
        RemoveLinkRequest request = new RemoveLinkRequest();
        request.setLink("http://example.com");
        LinkResponse mockResponse = new LinkResponse();
        mockResponse.setId(1);
        Mockito.when(client.removeLink(12345L, request)).thenReturn(Mono.just(mockResponse));

        Mono<LinkResponse> result = client.removeLink(12345L, request);

        StepVerifier.create(result)
            .expectNextMatches(response -> response.getId() == 1)
            .verifyComplete();
    }
}

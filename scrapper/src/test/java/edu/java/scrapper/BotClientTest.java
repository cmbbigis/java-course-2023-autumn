package edu.java.scrapper;

import edu.java.api.BotClient;
import edu.java.api.request.AddLinkRequest;
import edu.java.api.request.RemoveLinkRequest;
import edu.java.api.response.LinkResponse;
import edu.java.api.response.ListLinksResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Collections;

public class BotClientTest {

    @Test
    public void testRegisterChat() {
        BotClient client = Mockito.mock(BotClient.class);
        Mockito.when(client.registerChat(12345L)).thenReturn(Mono.empty());

        Mono<Void> result = client.registerChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testDeleteChat() {
        BotClient client = Mockito.mock(BotClient.class);
        Mockito.when(client.deleteChat(12345L)).thenReturn(Mono.empty());

        Mono<Void> result = client.deleteChat(12345L);

        StepVerifier.create(result)
            .expectComplete()
            .verify();
    }

    @Test
    public void testGetAllLinks() {
        BotClient client = Mockito.mock(BotClient.class);
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
        BotClient client = Mockito.mock(BotClient.class);
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
        BotClient client = Mockito.mock(BotClient.class);
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

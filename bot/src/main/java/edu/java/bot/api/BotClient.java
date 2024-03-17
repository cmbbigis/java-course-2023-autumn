package edu.java.bot.api;

import edu.java.bot.api.request.AddLinkRequest;
import edu.java.bot.api.request.RemoveLinkRequest;
import edu.java.bot.api.response.LinkResponse;
import edu.java.bot.api.response.ListLinksResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class BotClient {

    private final WebClient webClient;
    private final String tgChat = "/tg-chat/";
    private final String tgChatId = "Tg-Chat-Id";
    private final String links = "/links";

    public BotClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();
    }

    public Mono<Void> registerChat(long id) {
        return webClient.post()
            .uri(tgChat + id)
            .retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<Void> deleteChat(long id) {
        return webClient.delete()
            .uri(tgChat + id)
            .retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<ListLinksResponse> getAllLinks(long chatId) {
        return webClient.get()
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .retrieve()
            .bodyToMono(ListLinksResponse.class);
    }

    public Mono<LinkResponse> addLink(long chatId, AddLinkRequest request) {
        return webClient.post()
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .bodyValue(request)
            .retrieve()
            .bodyToMono(LinkResponse.class);
    }

    public Mono<LinkResponse> removeLink(long chatId, RemoveLinkRequest request) {
        return webClient.method(HttpMethod.DELETE)
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(LinkResponse.class);
    }
}

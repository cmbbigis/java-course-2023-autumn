package edu.java.client;

import edu.java.api.request.LinkUpdate;
import edu.java.client.policy.RetryPolicy;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BotClient {
    private final WebClient webClient;
    private final int three = 3;
    private final int thousand = 1000;

    public BotClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();
    }

    public Mono<LinkUpdate> postUpdate(LinkUpdate update) {
        return webClient.post()
            .uri("/updates")
            .bodyValue(update)
            .retrieve()
            .bodyToMono(LinkUpdate.class)
            .retryWhen(RetryPolicy.getPolicy("constant", three, thousand));
    }
}

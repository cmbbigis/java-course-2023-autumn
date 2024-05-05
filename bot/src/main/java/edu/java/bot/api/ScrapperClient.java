package edu.java.bot.api;

import edu.java.bot.api.request.AddLinkRequest;
import edu.java.bot.api.request.RemoveLinkRequest;
import edu.java.bot.api.response.LinkResponse;
import edu.java.bot.api.response.ListLinksResponse;
import java.time.Duration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;


public class ScrapperClient {
    private final WebClient webClient;
    private final String tgChat = "/tg-chat/";
    private final String tgChatId = "Tg-Chat-Id";
    private final String links = "/links";
    private final String constant = "constant";
    private final int ten = 10;
    private final int three = 3;
    private final int thousand = 1000;

    public ScrapperClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();
    }

    private Retry retryPolicy(String policy, int maxAttempts, long delay) {
        return switch (policy) {
            case constant -> Retry.fixedDelay(maxAttempts, Duration.ofMillis(delay))
                    .filter((ex) -> ex instanceof WebClientResponseException
                        && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            case "linear" -> Retry.backoff(maxAttempts, Duration.ofMillis(delay))
                    .filter((ex) -> ex instanceof WebClientResponseException
                        && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            case "exponential" -> Retry.backoff(maxAttempts, Duration.ofMillis(delay))
                    .maxBackoff(Duration.ofSeconds(ten))
                    .filter((ex) -> ex instanceof WebClientResponseException
                        && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            default -> throw new IllegalArgumentException("Invalid retry policy");
        };
    }

    public Mono<Void> registerChat(long id) {
        return webClient.post()
            .uri(tgChat + id)
            .retrieve()
            .bodyToMono(Void.class)
            .retryWhen(retryPolicy(constant, three, thousand));
    }

    public Mono<Void> deleteChat(long id) {
        return webClient.delete()
            .uri(tgChat + id)
            .retrieve()
            .bodyToMono(Void.class)
            .retryWhen(retryPolicy(constant, three, thousand));
    }

    public Mono<ListLinksResponse> getAllLinks(long chatId) {
        return webClient.get()
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .retrieve()
            .bodyToMono(ListLinksResponse.class)
            .retryWhen(retryPolicy(constant, three, thousand));
    }

    public Mono<LinkResponse> addLink(long chatId, AddLinkRequest request) {
        return webClient.post()
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .bodyValue(request)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .retryWhen(retryPolicy(constant, three, thousand));
    }

    public Mono<LinkResponse> removeLink(long chatId, RemoveLinkRequest request) {
        return webClient.method(HttpMethod.DELETE)
            .uri(links)
            .header(tgChatId, String.valueOf(chatId))
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .retryWhen(retryPolicy(constant, three, thousand));
    }
}

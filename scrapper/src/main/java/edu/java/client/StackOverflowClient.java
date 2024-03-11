package edu.java.client;

import edu.java.client.response.StackOverflowApiResponse;
import edu.java.client.response.StackOverflowQuestionResponse;
import edu.java.configuration.ClientConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackOverflowClient {
    private final WebClient stackOverflowWebClient;

    public StackOverflowClient(ClientConfiguration config) {
        this.stackOverflowWebClient = config.stackOverflowWebClient();
    }

    public Mono<StackOverflowQuestionResponse> fetchQuestion(Long questionId) {
        return stackOverflowWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/questions/{questionId}")
                .queryParam("site", "stackoverflow")
                .build(questionId))
            .retrieve()
            .bodyToMono(StackOverflowApiResponse.class)
            .map(response -> response.getItems().get(0));
    }
}

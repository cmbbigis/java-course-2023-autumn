package edu.java.client;

import edu.java.client.response.StackOverflowQuestionResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackOverflowClient {
    private final WebClient stackOverflowWebClient;

    public StackOverflowClient() {
        this.stackOverflowWebClient = WebClient.create("http://localhost:8089");
    }

    public Mono<StackOverflowQuestionResponse> fetchQuestion(Long questionId) {
        return stackOverflowWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/questions/{id}").build(questionId))
            .retrieve()
            .bodyToMono(StackOverflowQuestionResponse.class);
    }
}

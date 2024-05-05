package edu.java.client.policy;

import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

public class RetryPolicy {
    private RetryPolicy() {
    }

    private final static int TEN = 10;

    public static Retry getPolicy(String policy, int maxAttempts, long delay) {
        return switch (policy) {
            case "constant" -> Retry.fixedDelay(maxAttempts, Duration.ofMillis(delay))
                .filter((ex) -> ex instanceof WebClientResponseException
                    && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            case "linear" -> Retry.backoff(maxAttempts, Duration.ofMillis(delay))
                .filter((ex) -> ex instanceof WebClientResponseException
                    && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            case "exponential" -> Retry.backoff(maxAttempts, Duration.ofMillis(delay))
                .maxBackoff(Duration.ofSeconds(TEN))
                .filter((ex) -> ex instanceof WebClientResponseException
                    && ((WebClientResponseException) ex).getStatusCode().is5xxServerError());
            default -> throw new IllegalArgumentException("Invalid retry policy");
        };
    }
}

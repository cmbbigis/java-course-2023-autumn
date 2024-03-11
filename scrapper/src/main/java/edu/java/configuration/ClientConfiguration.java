package edu.java.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @NotEmpty
    private final String gitHubBaseUrl = "https://api.github.com";

    @NotEmpty
    private final String stackOverflowBaseUrl = "https://api.stackexchange.com/2.2";

    @Bean
    public WebClient gitHubWebClient() {
        return WebClient.builder().baseUrl(gitHubBaseUrl).build();
    }

    @Bean
    public WebClient stackOverflowWebClient() {
        return WebClient.builder().baseUrl(stackOverflowBaseUrl).build();
    }
}

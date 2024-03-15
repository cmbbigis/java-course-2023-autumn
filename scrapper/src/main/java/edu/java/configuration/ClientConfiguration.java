package edu.java.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    private ClientConfiguration() {

    }

    @NotEmpty
    private static final String GITHUB_BASE_URL = "https://api.github.com";

    @NotEmpty
    private static final String STACKOVERFLOW_BASE_URL = "https://api.stackexchange.com/2.2";

    @Bean
    public static WebClient gitHubWebClient() {
        return WebClient.builder().baseUrl(GITHUB_BASE_URL).build();
    }

    @Bean
    public static WebClient stackOverflowWebClient() {
        return WebClient.builder().baseUrl(STACKOVERFLOW_BASE_URL).build();
    }
}

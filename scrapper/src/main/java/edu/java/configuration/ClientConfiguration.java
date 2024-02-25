package edu.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public WebClient gitHubWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("https://api.github.com").build();
    }

    @Bean
    public WebClient stackOverflowWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("https://api.stackexchange.com/2.2").build();
    }
}

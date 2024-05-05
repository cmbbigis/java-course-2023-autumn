package edu.java.configuration;

import edu.java.api.BotClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Value("${github.base.url:https://api.github.com}")
    private String gitHubBaseUrl;

    @Value("${stackoverflow.base.url:https://api.stackexchange.com/2.2}")
    private String stackOverflowBaseUrl;

    @Value("${bot.base.url:http://localhost:8090}")
    private String botBaseUrl;

    @Bean
    public BotClient botClient() {
        return new BotClient(botBaseUrl);
    }

    @Bean
    public WebClient gitHubWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(gitHubBaseUrl).build();
    }

    @Bean
    public WebClient stackOverflowWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(stackOverflowBaseUrl).build();
    }
}

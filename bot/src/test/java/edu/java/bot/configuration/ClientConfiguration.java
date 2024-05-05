package edu.java.bot.configuration;

import edu.java.bot.api.ScrapperClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
    @Value("${scrapper.base.url:http://localhost:8080}")
    private String scrapperBaseUrl;

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient(scrapperBaseUrl);
    }
}

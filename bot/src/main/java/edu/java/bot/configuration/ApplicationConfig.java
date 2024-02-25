package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter @Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class ApplicationConfig {
    @NotEmpty
    @Value("${app.telegram-token}")
    private String telegramToken;
}

package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @Getter @NotEmpty
    @Value("${app.telegram-token}")
    String telegramToken
) {

}

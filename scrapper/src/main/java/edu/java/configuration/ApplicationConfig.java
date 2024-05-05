package edu.java.configuration;

import edu.java.LinkUpdaterScheduler;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull
    Scheduler scheduler,

    AccessType databaseAccessType
) {
    @Bean
    public LinkUpdaterScheduler linkUpdaterScheduler() {
        return new LinkUpdaterScheduler();
    }

    public record Scheduler(boolean enable, @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }
}

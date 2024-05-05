package edu.java.bot.configuration;

import edu.java.bot.service.KafkaConsumerService;
import edu.java.bot.service.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
@RequiredArgsConstructor
public class KafkaConfiguration implements KafkaListenerConfigurer {

    private final LocalValidatorFactoryBean validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(this.validator);
    }

    @Bean
    public KafkaConsumerService scrapperListener(UpdateHandler updateHandler) {
        return new KafkaConsumerService(updateHandler);
    }
}

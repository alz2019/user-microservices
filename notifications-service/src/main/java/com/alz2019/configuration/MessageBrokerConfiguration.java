package com.alz2019.configuration;

import com.alz2019.event.UserUpdatedEvent;
import com.alz2019.service.UserClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MessageBrokerConfiguration {
    private final UserClientService userClientService;

    @Bean
    public Consumer<UserUpdatedEvent> updatedEventConsumer() {
        return updatedEvent -> {
            log.info("Received event: {}", updatedEvent);
            userClientService.syncUserById(updatedEvent.id());
        };
    }
}

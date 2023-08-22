package com.alz2019.config;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

import static org.springframework.messaging.support.MessageBuilder.createMessage;

@Configuration
public class ProducerMessageVerifier {
    private final Logger logger = LoggerFactory.getLogger(ProducerMessageVerifier.class);

    @Bean
    MessageVerifierSender<Message<?>> standaloneMessageVerifier(StreamBridge streamBridge) {
        return new MessageVerifierSender<>() {
            @Override
            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
                streamBridge.send("updatedEventConsumer-in-0", message);
            }

            @Override
            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
                Message<T> message = createMessage(payload, new MessageHeaders(headers));
                logger.info("Sending entity: %s".formatted(message));
                streamBridge.send("updatedEventConsumer-in-0", message);
            }
        };
    }

    @Bean
    @Primary
    JsonMessageConverter messageConverter() {
        return new NoOpMessageConverter();
    }
}

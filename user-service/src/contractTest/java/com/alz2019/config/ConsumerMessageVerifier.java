package com.alz2019.config;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyMap;
import static java.util.Collections.synchronizedSet;
import static org.springframework.messaging.support.MessageBuilder.createMessage;

@Configuration
public class ConsumerMessageVerifier implements MessageVerifierReceiver<Message<?>> {
    private final Set<Message<?>> consumedMessages = synchronizedSet(new HashSet<>());
    private final Logger logger = LoggerFactory.getLogger(ConsumerMessageVerifier.class);

    @KafkaListener(topics = {"user-topic"}, groupId = "user-event-queue")
    void consumeMessage(String event) {
        consumedMessages.add(createMessage(event, new MessageHeaders(emptyMap())));
        logger.info(consumedMessages.toString());
    }

    @Override
    public Message<?> receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
        for (int i = 0; i < timeout; i++) {
            Message<?> consumedMessage = consumedMessages.stream().findAny().orElse(null);
            if (consumedMessage != null) {
                return consumedMessage;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        return null;
    }

    @Override
    public Message<?> receive(String destination, YamlContract contract) {
        return receive(destination, 2, TimeUnit.SECONDS, contract);
    }
}

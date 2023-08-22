package com.alz2019.config;

import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;

public class NoOpMessageConverter extends JsonMessageConverter {
    public NoOpMessageConverter() {
    }

    @Override
    protected Object convertPayload(Message<?> message) {
        return message.getPayload();
    }
}

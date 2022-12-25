package com.alz2019.dto;

import java.util.UUID;

public record NotificationDto(UUID id, String title, String body) {
}

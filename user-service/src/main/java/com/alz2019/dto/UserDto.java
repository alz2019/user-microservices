package com.alz2019.dto;

import java.util.List;

public record UserDto(String firstName, String lastName, List<NotificationDto> notifications) {
}

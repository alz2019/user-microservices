package com.alz2019.client;

import com.alz2019.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "notifications-service")
public interface NotificationClient {
    @GetMapping("/notifications/{id}/user")
    List<NotificationDto> getNotificationsByUserId(@PathVariable UUID id);
}

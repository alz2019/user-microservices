package com.alz2019.controller;

import com.alz2019.entity.Notification;
import com.alz2019.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Notification getOne(@PathVariable UUID id) {
        return notificationRepository.findById(id)
                .orElseThrow();
    }

    @GetMapping("/{userId}/user")
    public List<Notification> getOneByUserId(@PathVariable UUID userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody Notification notification) {
        if (!Objects.equals(id, notification.getId())) {
            throw new IllegalStateException("Id parameter does not match notification body value");
        }
        notificationRepository.save(notification);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        Notification account = notificationRepository.findById(id)
                .orElseThrow();
        notificationRepository.delete(account);
    }
}

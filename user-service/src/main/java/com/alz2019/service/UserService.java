package com.alz2019.service;

import com.alz2019.client.NotificationClient;
import com.alz2019.dto.NotificationDto;
import com.alz2019.dto.UserDto;
import com.alz2019.entity.User;
import com.alz2019.event.UserUpdatedEvent;
import com.alz2019.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final NotificationClient notificationClient;
    private final StreamBridge streamBridge;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public User saveUser(User user) {
        // Generate UUID
        user.setId(UUID.randomUUID());

        // Local update
        User savedUser = userRepository.save(user);

        // Publish event
        streamBridge.send("user-topic", new UserUpdatedEvent(user.getId()));

        // Return saved user
        return savedUser;
    }

    public void updateUser(User user) {
        // Local update
        userRepository.save(user);

        // Publish event
        streamBridge.send("user-topic", new UserUpdatedEvent(user.getId()));
    }

    public void removeUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        userRepository.delete(user);
    }

    public UserDto getWithNotifications(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        List<NotificationDto> notifications = notificationClient.getNotificationsByUserId(userId);
        return new UserDto(user.getFirstName(), user.getLastName(), notifications);
    }
}

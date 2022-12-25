package com.alz2019.service;

import com.alz2019.client.UserClient;
import com.alz2019.entity.User;
import com.alz2019.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserClientService {
    private final UserClient userClient;
    private final UserRepository userRepository;

    public void syncUserById(UUID userId) {
        User user = userClient.getUserById(userId);
        userRepository.save(user);
    }
}

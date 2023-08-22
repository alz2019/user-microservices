package com.alz2019.integration;

import com.alz2019.client.UserClient;
import com.alz2019.config.IntegrationTestBaseConfiguration;
import com.alz2019.entity.User;
import com.alz2019.repository.UserRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.alz2019:user-service:+:stubs")
public class HandleUserUpdatedEventIT extends IntegrationTestBaseConfiguration {
    @Autowired
    private StubFinder stubFinder;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserClient userClient;

    @Test
    void handleEvent() {
        when(userClient.getUserById(any(UUID.class))).thenAnswer((Answer<User>) invocation -> {
            User user = new User();
            user.setId(invocation.getArgument(0));
            user.setFirstName("Todd");
            user.setLastName("Bennett");
            return user;
        });

        stubFinder.trigger("shouldPublishUserCreated");

        Awaitility.await().untilAsserted(() -> {
            Optional<User> optionalUser = userRepository.findAll().stream().findAny();
            Assertions.assertTrue(optionalUser.isPresent());
            User user = optionalUser.get();
            Assertions.assertEquals("Todd", user.getFirstName());
            Assertions.assertEquals("Bennett", user.getLastName());
            Assertions.assertNotNull(user.getId());
        });
    }
}

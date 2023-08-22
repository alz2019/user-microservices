package com.alz2019.contracts;

import com.alz2019.config.IntegrationTestBaseConfiguration;
import com.alz2019.entity.User;
import com.alz2019.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMessageVerifier
public class ShouldPublishUserCreatedBase extends IntegrationTestBaseConfiguration {
    @Autowired
    private UserService userService;

    void shouldPublishUserCreated() {
        var user = new User();
        user.setFirstName("Sherry");
        user.setLastName("Torres");
        User createdUser = userService.saveUser(user);
        Assertions.assertNotNull(createdUser);
    }
}

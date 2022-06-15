package com.digiboy.platform.auth.service.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EmailConfiguration.class)
class EmailConfigurationTest {

    @Autowired
    EmailConfiguration emailConfiguration;

    @Test
    void shouldWork(){
        Assertions.assertNotNull(emailConfiguration.getPattern());
        System.out.println(emailConfiguration.getPattern());
    }

}
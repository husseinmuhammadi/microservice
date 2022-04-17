package com.digiboy.platform.user.web.config.mapper;

import com.digiboy.platform.user.generated.v1.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MapperConfigurationTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    void itShouldWork() throws JsonProcessingException {
        User user = new User().userId(UUID.randomUUID());
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
    }
}
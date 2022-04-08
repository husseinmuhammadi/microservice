package com.digiboy.platform.users.web.config.mapper;

import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.generated.v1.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
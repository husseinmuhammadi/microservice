package com.digiboy.platform.user.web.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UsersResourceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldRejectWithBadRequest_whenRequestWithMalformedEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{email}/email", "incorrect-email"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldRejectWithNotFound_whenUserNotFound() throws Exception {

        final String email = "info@example.com";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{email}/email", email))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}

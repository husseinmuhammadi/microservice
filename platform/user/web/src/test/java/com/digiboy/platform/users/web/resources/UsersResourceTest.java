package com.digiboy.platform.users.web.resources;

import com.digiboy.platform.users.api.UserService;
import com.digiboy.platform.users.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.users.web.config.mapper.MapperConfiguration;
import com.digiboy.platform.users.web.mapper.CreateUserMapperImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStream;

@WebMvcTest
@Import({CreateUserMapperImpl.class, MapperConfiguration.class})
class UsersResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;

    @MockBean
    private Logger logger;

    @TestConfiguration
    static class SpringContextConfiguration {
        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Test
    void name() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content("{\"username\":\"1006\",\"password\":\"1\", \"confirmPassword\":\"2\",\"email\":\"hossein.mohammadi@outlook.com\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @ParameterizedTest
    @ValueSource(strings = "mock-data/create-user.json")
    void itShouldWork(String resourceName) throws Exception {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            CreateUserRequest request = mapper.readValue(in, CreateUserRequest.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsBytes(request)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(ResultMatcher.matchAll(
                            MockMvcResultMatchers.status().isCreated()
                    ));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "mock-data/create-user-email-malformed.json",
            "mock-data/create-user-empty-email.json",
    })
    void itWillReject(String resourceName) throws Exception {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            CreateUserRequest request = mapper.readValue(in, CreateUserRequest.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsBytes(request)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(ResultMatcher.matchAll(
                            MockMvcResultMatchers.status().isBadRequest()
                    ));
        }
    }
}
package com.digiboy.platform.user.web.resources;

import com.digiboy.platform.user.api.UserService;
import com.digiboy.platform.user.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.user.web.config.mapper.MapperConfiguration;
import com.digiboy.platform.user.web.mapper.UserModelMapperImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@Import({UserModelMapperImpl.class, MapperConfiguration.class})
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
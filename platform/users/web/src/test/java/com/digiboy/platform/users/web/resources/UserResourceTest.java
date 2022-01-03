package com.digiboy.platform.users.web.resources;

import com.digiboy.platform.users.api.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest {

//    @Autowired
//    private UserResource userResource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    void name() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .content("{\"username\":\"1006\",\"password\":\"1\", \"confirmPassword\":\"2\",\"email\":\"hossein.mohammadi@outlook.com\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );
    }

    @Test
    void name1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"));
    }
}
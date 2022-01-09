package com.digiboy.platform.auth;

import com.digiboy.platform.auth.web.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    private Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenSendHttpRequest_thenFilterShouldCatchTheRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/check"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void sendLoginRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hmohammadi");
        loginRequest.setPassword("123456");
        mockMvc.perform(MockMvcRequestBuilders.post("/login?username=hossein")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest))
        ).andDo(MockMvcResultHandlers.print());
        System.out.println("---");
    }
}

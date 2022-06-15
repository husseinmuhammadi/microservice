package com.digiboy.platform.auth.web.config.mapper;

import com.digiboy.platform.auth.generated.v1.model.LoginRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class JsonJacksonObjectMapperTest {

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void shouldThrowUnrecognizedPropertyException_whenDeserializeUnknownProperties() throws IOException {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("mock-data/login-request-with-unrecognized-property.json")) {
            Assertions.assertThrows(UnrecognizedPropertyException.class, () -> {
                mapper.readValue(in, LoginRequest.class);
            });
        }
    }

    @Test
    void shouldNotThrowUnrecognizedPropertyException_whenDeserializeUnknownProperties() throws IOException {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("mock-data/login-request-with-unrecognized-property.json")) {
            Assertions.assertDoesNotThrow(() -> {
                print(mapper.readValue(in, LoginRequest.class), System.out);
            });
        }
    }

    @Test
    void x() throws IOException {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("mock-data/login-request-with-username-and-password.json")) {
            Assertions.assertDoesNotThrow(() -> {
                print(mapper.readValue(in, LoginRequest.class), System.out);
            });
        }
    }

    private void print(Object o, OutputStream out) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, o);
    }
}

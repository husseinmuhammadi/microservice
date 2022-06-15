package com.digiboy.platform.auth;

import com.digiboy.platform.auth.generated.v1.model.LoginByEmailRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class FakeTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void validateLoginRequestByEmail() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("mock-data/login-request-with-email-and-password.json")) {
            LoginByEmailRequest loginByEmailRequest = objectMapper.readValue(in, LoginByEmailRequest.class);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<LoginByEmailRequest>> violations = validator.validate(loginByEmailRequest);

            Assertions.assertThat(violations).isEmpty();
        }
    }
}

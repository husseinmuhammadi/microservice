package com.eightbitsolutions.infrastructure.gateway.filters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorizationHeaderFilter.class)
class AuthorizationHeaderFilterTest {

    @Autowired
    AuthorizationHeaderFilter authorizationHeaderFilter;

    @Test
    void shouldExistsInBean() {
        assertThat(authorizationHeaderFilter).isNotNull();
    }
}
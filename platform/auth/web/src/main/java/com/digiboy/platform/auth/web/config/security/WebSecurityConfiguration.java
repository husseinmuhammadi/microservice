package com.digiboy.platform.auth.web.config.security;

import com.digiboy.platform.auth.api.UserService;
import com.digiboy.platform.auth.web.config.security.filters.AuthenticationFilter;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity(debug = false)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] SWAGGER_UI_V2 = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    private static final String[] Swagger_UI_v3_OpenAPI = {
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private final UserService userService;
    private final Environment env;

    public WebSecurityConfiguration(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new AuthenticationFilter(authenticationManager(), env))
                .authorizeRequests()
                .antMatchers("/actuator/*", "/login", "/check").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_UI_V2);
    }
}

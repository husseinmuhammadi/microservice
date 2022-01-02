package com.digiboy.platform.users.web.resources;

import com.digiboy.platform.users.api.UserService;
import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.web.mapper.CreateUserMapper;
import com.digiboy.platform.users.web.model.CreateUserRequest;
import com.digiboy.platform.users.web.model.CreateUserResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final Logger logger;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private CreateUserMapper mapper;

    public UserResource(Logger logger, UserService service, PasswordEncoder passwordEncoder) {
        this.logger = logger;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public CreateUserResponse save(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserDTO user = mapper.map(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        return mapper.map(service.save(user));
    }
}

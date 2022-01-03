package com.digiboy.platform.users.web.resources;

import com.digiboy.platform.users.api.UserService;
import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.web.mapper.CreateUserMapper;
import com.digiboy.platform.users.web.model.CreateUserRequest;
import com.digiboy.platform.users.web.model.CreateUserResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping
    public String status(){
        System.out.println("+++++++++++++++++Hello");
        return "Hello";
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> save(@Valid @RequestBody CreateUserRequest createUserRequest) {
        System.out.println("+++++++++++++++++++++++User Controller ");
        UserDTO user = mapper.map(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        CreateUserResponse response = mapper.map(service.save(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

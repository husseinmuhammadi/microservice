package com.digiboy.platform.user.web.resources;

import com.digiboy.platform.user.api.UserService;
import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.generated.v1.api.UsersApi;
import com.digiboy.platform.user.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.user.generated.v1.model.CreateUserResponse;
import com.digiboy.platform.user.generated.v1.model.User;
import com.digiboy.platform.user.web.mapper.CreateUserMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersResource implements UsersApi {

    private final Logger logger;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private CreateUserMapper mapper;

    public UsersResource(Logger logger, UserService service, PasswordEncoder passwordEncoder) {
        this.logger = logger;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<List<User>> findUsers(String username, String email) {
        return UsersApi.super.findUsers(username, email);
    }

    @Override
    public ResponseEntity<CreateUserResponse> saveUser(CreateUserRequest createUserRequest) {
        UserDTO user = mapper.map(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        CreateUserResponse response = mapper.map(service.save(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

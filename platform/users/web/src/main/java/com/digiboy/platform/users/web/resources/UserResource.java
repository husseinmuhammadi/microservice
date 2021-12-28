package com.digiboy.platform.users.web.resources;

import com.digiboy.platform.users.api.UserService;
import com.digiboy.platform.users.dto.UserDTO;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final Logger logger;
    private final UserService service;

    public UserResource(Logger logger, UserService service) {
        this.logger = logger;
        this.service = service;
    }

    @PostMapping
    public UserDTO save(@RequestBody UserDTO user) {
        return service.save(user);
    }

}

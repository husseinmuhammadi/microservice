package com.digiboy.platform.auth.web.client;

import com.digiboy.platform.auth.generated.v1.api.UsersApi;
import com.digiboy.platform.auth.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.auth.generated.v1.model.CreateUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "users")

public interface UsersService extends UsersApi {

    @GetMapping("/users/index")
    String index();

    @Override
    @PostMapping(
            value = "/api/v1/users",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<CreateUserResponse> saveUser(CreateUserRequest createUserRequest);
}

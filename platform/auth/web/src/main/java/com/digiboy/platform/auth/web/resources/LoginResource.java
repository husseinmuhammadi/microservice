package com.digiboy.platform.auth.web.resources;

import com.digiboy.platform.auth.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.auth.generated.v1.model.CreateUserResponse;
import com.digiboy.platform.auth.web.client.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginResource {

    @Autowired
    UsersService usersService;

    @GetMapping("/check")
    public String check(){
        ResponseEntity<CreateUserResponse> response = usersService.saveUser(new CreateUserRequest().email("g@g").password("1").confirmPassword("1"));
        System.out.println("--->"+response.getStatusCode());
        return usersService.index();
    }
}

package com.digiboy.platform.auth.web.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginResource {
    @GetMapping("/check")
    public String check(){
        return "Hello";
    }
}

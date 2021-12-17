package com.eightbitsolutions.platform.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class HomeResource {

    @Autowired
    private Environment env;

    @GetMapping("/index")
    public String index() {
        return "Working on port " + env.getProperty("local.server.port");
    }
}

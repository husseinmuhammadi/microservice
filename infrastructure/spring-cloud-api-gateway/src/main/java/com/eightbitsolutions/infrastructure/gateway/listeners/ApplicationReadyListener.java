package com.eightbitsolutions.infrastructure.gateway.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    }
}

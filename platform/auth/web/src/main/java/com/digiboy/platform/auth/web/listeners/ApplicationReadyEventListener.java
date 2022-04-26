package com.digiboy.platform.auth.web.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationReadyEventListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(beanName -> LOGGER.info("{}", beanName));
    }
}

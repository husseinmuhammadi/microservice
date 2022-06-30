package com.eightbitsolutions.infrastructure.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

    private final Logger logger= LoggerFactory.getLogger(GlobalFilterConfiguration.class);

    @Order(3)
    @Bean
    public GlobalFilter secondGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("Second pre filter executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                logger.info("Second post filter executed ...");
            }));
        };
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("Third pre filter executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                logger.info("Third post filter executed ...");
            }));
        };
    }

    @Order(4)
    @Bean
    public GlobalFilter fourthGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("Fourth pre filter executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                logger.info("Fourth post filter executed ...");
            }));
        };
    }
}
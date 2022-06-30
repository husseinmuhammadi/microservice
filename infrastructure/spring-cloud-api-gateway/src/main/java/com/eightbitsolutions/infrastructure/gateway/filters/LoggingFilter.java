package com.eightbitsolutions.infrastructure.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Pre-filter with lower value will run before the pre-filter with higher value
 */
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("First global pre filter executed ...");

        ServerHttpRequest request = exchange.getRequest();

        logger.info("Request path: {}", request.getPath());

        HttpHeaders headers = request.getHeaders();
        Set<String> headerNames = headers.keySet();

        headerNames.forEach(headerName -> {
            Optional.ofNullable(headers.get(headerName)).map(List::toArray).map(Arrays::toString).ifPresent(values -> {
                logger.info("{} --> {}", headerName, values);
            });
        });

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

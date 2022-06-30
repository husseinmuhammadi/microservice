package com.eightbitsolutions.infrastructure.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Post-filter with the with lower value will run after the post-filter with higher value
 */
@Component
public class GlobalPostFilter implements GlobalFilter, Ordered {

    private final Logger logger= LoggerFactory.getLogger(GlobalPostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("First global post filter executed ...");
        }));
    }

    // The order with lower value has higher priority
    @Override
    public int getOrder() {
        return 0;
    }
}

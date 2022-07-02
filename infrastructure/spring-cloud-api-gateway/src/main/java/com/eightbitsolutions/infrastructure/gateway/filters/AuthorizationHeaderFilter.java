package com.eightbitsolutions.infrastructure.gateway.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationHeaderFilter.class);

    private final Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String jwt = Optional.ofNullable(request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0))
                    .map(authorizationHeader -> authorizationHeader.replace("Bearer ", ""))
                    .orElse(null);

            try {
                if (!isJwtValid(jwt)) {
                    return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
                }
            } catch (ExpiredJwtException e) {
                // logger.warn("Jwt token is expired", e);
                return onError(exchange, "JWT token is expired", HttpStatus.UNAUTHORIZED);
            } catch (JwtException e) {
                // logger.error("JWT could not be validated", e);
                return onError(exchange, "JWT could not be validated", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isJwtValid(String jwt) {
        byte[] secret = env.getProperty("token.secret").getBytes(StandardCharsets.UTF_8);

        String subject = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(jwt).getBody().getSubject();

        if (subject == null || subject.isEmpty())
            return false;

        return true;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }

    public static class Config {

    }
}

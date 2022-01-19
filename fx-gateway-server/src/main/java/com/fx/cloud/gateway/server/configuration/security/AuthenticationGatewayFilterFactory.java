package com.fx.cloud.gateway.server.configuration.security;

import com.fx.cloud.gateway.server.constants.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author xun.guo
 */
@Slf4j
@Service
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private Logger logger = LoggerFactory.getLogger(AuthenticationGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Object config) {
        log.info("------------------AuthenticationGatewayFilterFactory-------------------");
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String token = exchange.getResponse().getHeaders().getFirst(AuthConstant.AUTHORITY_KEY);
            log.info("token = {}", token);
        }));
    }
}

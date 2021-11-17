package com.fx.cloud.gateway.server.configuration.security;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 资源服务器配置
 *
 * @author xun.guo
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class ResourceServerConfig {
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final AuthorizationManager authorizationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                // 白名单配置
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                .and().csrf().disable();
        return http.build();
    }
}

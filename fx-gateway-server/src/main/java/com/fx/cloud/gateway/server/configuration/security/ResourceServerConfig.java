package com.fx.cloud.gateway.server.configuration.security;

import cn.hutool.core.util.ArrayUtil;
import com.fx.cloud.gateway.server.handler.FxAccessDeniedHandler;
import com.fx.cloud.gateway.server.handler.FxAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.WebFilter;

import java.util.Iterator;

/**
 * 资源服务器配置
 *
 * @author xun.guo
 */
@AllArgsConstructor
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(new FxAuthenticationConverter(tokenStore));

        http.authorizeExchange()
                // 白名单配置
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(new AuthorizationManager(tokenStore))
                .and()
                .exceptionHandling()
//                AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
                .accessDeniedHandler(new FxAccessDeniedHandler())
//                配置entrypoint 禁止页面弹出登录框   AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(new FxAuthenticationEntryPoint())
                .and()
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable();
        SecurityWebFilterChain chain = http.build();
        Iterator<WebFilter> weIterable = chain.getWebFilters().toIterable().iterator();
        while (weIterable.hasNext()) {
            WebFilter f = weIterable.next();
            if (f instanceof AuthenticationWebFilter) {
                AuthenticationWebFilter webFilter = (AuthenticationWebFilter) f;
                //将自定义的AuthenticationConverter添加到过滤器中
                webFilter.setServerAuthenticationConverter(new FxAuthenticationConverter(tokenStore));
            }
        }
        return chain;
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new ReactiveAuthenticationManagerAdapter(authentication -> {
            if (authentication instanceof FxAccountAuthentication) {
                FxAccountAuthentication fxAccountAuthentication = (FxAccountAuthentication) authentication;
                if (!ObjectUtils.isEmpty(fxAccountAuthentication)) {
                    if (fxAccountAuthentication.getPrincipal() != null && !CollectionUtils.isEmpty(fxAccountAuthentication.getAuthorities())) {
                        authentication.setAuthenticated(true);
                        return authentication;
                    }
                }
            } else {
                authentication.setAuthenticated(true);
                return authentication;
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
    }

}

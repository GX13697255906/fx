package com.fx.cloud.gateway.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author xun.guo
 */
@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        return null;
    }
}

package com.fx.cloud.gateway.server.configuration.security;

import com.fx.cloud.common.entity.security.FxAuthority;
import com.fx.cloud.common.entity.security.FxUserDetails;
import com.fx.cloud.gateway.server.constants.AuthConstant;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author xun.guo
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private TokenStore tokenStore;

    public AuthorizationManager( TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
// 1、从Redis中获取当前路径可访问角色列表
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        String token = request.getHeaders().getFirst(AuthConstant.AUTHORITY_KEY);
        Set<FxAuthority> authoritySet = Sets.newHashSet();
        FxUserDetails userDetails = new FxUserDetails();
        if (!StringUtils.isEmpty(token)) {
            token = token.replace(AuthConstant.BEARER_TOKEN_PREFIX, "");
            String access_key = AuthConstant.AUTH_ACCESS_KEY + ":" + token;
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            if (!ObjectUtils.isEmpty(accessToken)) {
                OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
                userDetails = (FxUserDetails) oAuth2Authentication.getPrincipal();
                authoritySet = (HashSet<FxAuthority>) userDetails.getAuthorities();
            }
        }
        List<String> authorities = authoritySet.stream().map(e -> e.getAuthority()).collect(Collectors.toList());
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(auth->{
                    if(authorities.contains(path)){
                        return true;
                    }else {
                        return false;
                    }
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }
}

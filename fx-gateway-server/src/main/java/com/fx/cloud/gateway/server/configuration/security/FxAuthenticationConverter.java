package com.fx.cloud.gateway.server.configuration.security;

import com.fx.cloud.common.entity.security.FxAuthority;
import com.fx.cloud.common.entity.security.FxUserDetails;
import com.fx.cloud.gateway.server.constants.AuthConstant;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 */
@Slf4j
public class FxAuthenticationConverter implements ServerAuthenticationConverter {

    String credentials;
    TokenStore tokenStore;

    public FxAuthenticationConverter(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        //从session中获取登陆用户信息
        String value = null;
        FxAccountAuthentication fxAccountAuthentication = null;
        ServerHttpRequest request = exchange.getRequest();
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
                credentials = userDetails.getPassword();
                value = userDetails.getUsername();
                authoritySet = (HashSet<FxAuthority>) userDetails.getAuthorities();
            }
        }
        //添加用户信息到spring security之中。
        fxAccountAuthentication = new FxAccountAuthentication(credentials, value, authoritySet);
        return Mono.just(fxAccountAuthentication);
    }

}

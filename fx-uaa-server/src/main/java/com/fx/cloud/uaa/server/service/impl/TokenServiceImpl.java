package com.fx.cloud.uaa.server.service.impl;

import com.fx.cloud.uaa.server.entity.FxClientDetails;
import com.fx.cloud.uaa.server.service.TokenService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author xun.guo
 */
@Data
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AuthorizationServerEndpointsConfiguration endpoints;

    private FxClientDetails clientDetails = new FxClientDetails();

    public TokenServiceImpl() {
        clientDetails.setClientId("7gBZcbsC7kLIWCdELIl8nxcs");
        clientDetails.setClientSecret("$10$4di0sSQdr9yk4uTKWtZqzedsxI8sWXoR67x.G.Qmy4K2L7ZaFQt6W");
        clientDetails.setScope(Lists.newArrayList("all"));
        clientDetails.setAuthorizedGrantTypes(Lists.newArrayList("authorization_code", "client_credentials", "password", "refresh_token"));
        clientDetails.setAccessTokenValiditySeconds(43200);
        clientDetails.setRefreshTokenValiditySeconds(2592000);
    }

    @Override
    public OAuth2AccessToken getToken(String username, String password) throws Exception {
        Map<String, String> parameterMap = Maps.newHashMap();
        parameterMap.put("username", username);
        parameterMap.put("password", password);
        parameterMap.put("client_id", clientDetails.getClientId());
        parameterMap.put("client_secret", clientDetails.getClientSecret());
        parameterMap.put("grant_type", "password");

        OAuth2AccessToken token = createAccessToken(endpoints, parameterMap);
        return token;
    }

    /**
     * 认证服务器原始方式创建AccessToken
     *
     * @param endpoints
     * @param postParameters
     * @return
     * @throws Exception
     */
    public static OAuth2AccessToken createAccessToken(AuthorizationServerEndpointsConfiguration endpoints, Map<String, String> postParameters) throws Exception {
        Assert.notNull(postParameters.get("client_id"), "client_id not null");
        Assert.notNull(postParameters.get("client_secret"), "client_secret not null");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(postParameters.get("client_id"), postParameters.get("client_secret"), Collections.emptyList());
        ResponseEntity<OAuth2AccessToken> responseEntity = null;
        try {
            responseEntity = endpoints.tokenEndpoint().postAccessToken(auth, postParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        if (null == responseEntity) {
            return null;
        }
        return responseEntity.getBody();
    }
}

package com.fx.cloud.uaa.server.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @author xun.guo
 */
public interface TokenService {

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    OAuth2AccessToken getToken(String username, String password) throws Exception;

}

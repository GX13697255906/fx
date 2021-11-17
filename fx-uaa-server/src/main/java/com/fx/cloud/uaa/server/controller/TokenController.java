package com.fx.cloud.uaa.server.controller;

import com.fx.cloud.uaa.server.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xun.guo
 */
@Api(value = "用户登录", tags = "用户登录")
@RestController
@RequestMapping(value = "/login")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "获取token")
    @GetMapping(value = "/token")
    public OAuth2AccessToken getToken(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) throws Exception {
        return tokenService.getToken(username, password);
    }

}

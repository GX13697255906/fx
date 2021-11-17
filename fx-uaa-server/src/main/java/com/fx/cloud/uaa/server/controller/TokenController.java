package com.fx.cloud.uaa.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Api(value = "用户登录", tags = "用户登录")
@RestController
@RequestMapping(value = "/login")
public class TokenController {

    @ApiOperation(value = "获取token")
    @GetMapping(value = "/getToken")
    public void getToken(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

    }

}

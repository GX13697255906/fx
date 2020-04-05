package com.gx.fm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户登录验证", tags = "用户登录验证")
public class TokenController {

    @ApiOperation(value = "用户登录，获取token令牌",notes = "用户登录，获取token令牌")
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        return null;
    }

}

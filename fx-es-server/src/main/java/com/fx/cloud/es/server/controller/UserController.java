package com.fx.cloud.es.server.controller;


import com.fx.cloud.es.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Api(tags = "ES-用户管理", value = "ES-用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "插入用户信息", notes = "插入用户信息")
    @GetMapping(value = "/insertUserDoc")
    public void insertUsrDoc() {
        userService.insertUserDoc();
    }

}

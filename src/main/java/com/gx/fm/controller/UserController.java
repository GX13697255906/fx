package com.gx.fm.controller;


import com.gx.fm.constants.ParametersCenter;
import com.gx.fm.entity.User;
import com.gx.fm.service.UserService;
import com.gx.fm.utils.UtilId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@Api(value = "用户管理", tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//
//    @GetMapping(value = "/all")
//    @ApiOperation(value = "获取所有用户", notes = "获取所有用户")
//    public List<User> selAllUser(){
//        return userService.selUserList();
//    }

    @GetMapping(value = "/registery")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public Boolean addUser(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = new User();
        user.setId(UtilId.UUID());
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatetime(new Date());
        user.setStatus(ParametersCenter.Status_Enable);
        user.setType(ParametersCenter.User_Type_Normal);
        return userService.addUser(user);
    }


}


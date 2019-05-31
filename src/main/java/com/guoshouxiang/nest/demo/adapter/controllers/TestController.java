package com.guoshouxiang.nest.demo.adapter.controllers;

import com.guoshouxiang.nest.demo.context.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("demo")
public class TestController {

    @Autowired
    private UserService userService;


    @ApiOperation("hello")
    @GetMapping("/hello")
    public String hello() {
        userService.create("username", "pwdpwdpwd");
        userService.query();
        return "ok";
    }
}

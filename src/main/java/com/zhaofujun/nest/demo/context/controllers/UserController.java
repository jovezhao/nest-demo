package com.zhaofujun.nest.demo.context.controllers;

import com.zhaofujun.nest.demo.application.UserDto;
import com.zhaofujun.nest.demo.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void create(String userName, String pwd) {
        userService.create(userName, pwd);
    }


    @GetMapping("/get")
    public String get() {
        UserDto user = userService.get("22d4bdfc-94c9-4bbc-9124-985cca679e07");
        return user.getId();
    }


}


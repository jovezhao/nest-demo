package com.guoshouxiang.nest.demo.adapter.controllers;

import com.guoshouxiang.nest.demo.context.models.User;
import com.guoshouxiang.nest.demo.context.UserService;
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
        User user = userService.get("22d4bdfc-94c9-4bbc-9124-985cca679e07");
        return user.getId().toValue();
    }
}


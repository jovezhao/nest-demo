package com.zhaofujun.nest.demo.domain;

import com.zhaofujun.nest.context.model.StringIdentifier;
import com.zhaofujun.nest.core.BaseEntity;

public class User extends BaseEntity<StringIdentifier> {
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void init(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean verify() {
        if (password.length() < 6) return false;
        return true;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}

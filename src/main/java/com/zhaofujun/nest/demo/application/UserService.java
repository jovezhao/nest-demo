package com.zhaofujun.nest.demo.application;


import com.zhaofujun.nest.mybatis.paging.PageList;

public interface UserService {
    void create(String userName, String pwd);
    UserDto get(String id);
    PageList<UserDto> query();

    void changePassword(String id,String pwd);
}

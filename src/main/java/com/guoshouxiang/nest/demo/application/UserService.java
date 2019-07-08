package com.guoshouxiang.nest.demo.application;

import com.guohuoxiang.nest.mybatis.pagination.PageList;

public interface UserService {
    void create(String userName, String pwd);
    UserDto get(String id);
    PageList<UserDto> query();
}

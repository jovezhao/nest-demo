package com.zhaofujun.nest.demo.domain.queries;

import com.zhaofujun.nest.demo.domain.User;
import com.zhaofujun.nest.mybatis.paging.PageList;
import com.zhaofujun.nest.mybatis.paging.PageParames;

public interface UserQuery {
    PageList<User> getList(PageParames pageParames);
}

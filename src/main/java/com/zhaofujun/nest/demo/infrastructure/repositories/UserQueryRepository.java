package com.zhaofujun.nest.demo.infrastructure.repositories;

import com.zhaofujun.nest.context.loader.RepositoryEntityLoader;
import com.zhaofujun.nest.context.model.StringIdentifier;
import com.zhaofujun.nest.core.EntityLoader;
import com.zhaofujun.nest.demo.domain.User;
import com.zhaofujun.nest.demo.infrastructure.dao.queries.UserMapper;
import com.zhaofujun.nest.demo.domain.queries.UserQuery;
import com.zhaofujun.nest.mybatis.paging.PageList;
import com.zhaofujun.nest.mybatis.paging.PageParames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserQueryRepository implements UserQuery {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageList<User> getList(PageParames pageParames) {
        EntityLoader<User> userEntityLoader = new RepositoryEntityLoader<>(User.class);

        PageList<User> users = userMapper.getList(pageParames)
                .mapPageList(p -> userEntityLoader.create(StringIdentifier.valueOf(p)));
        return users;
    }
}

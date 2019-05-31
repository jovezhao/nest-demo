package com.guoshouxiang.nest.demo.context;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guohuoxiang.nest.mybatis.pagination.PageParames;
import com.guoshouxiang.nest.context.loader.EntityLoader;
import com.guoshouxiang.nest.context.loader.RepositoryEntityLoader;
import com.guoshouxiang.nest.context.model.StringIdentifier;
import com.guoshouxiang.nest.demo.context.models.User;
import com.guoshouxiang.nest.demo.context.queries.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryForUser {
    @Autowired
    private UserQuery userQuery;

    public PageList<User> getUserList(int page, int count) {
        EntityLoader<User> userEntityLoader = new RepositoryEntityLoader<>(User.class);

        PageList<User> users = userQuery.getList(PageParames.create(page, count))
                .mapPageList(p -> userEntityLoader.create(StringIdentifier.valueOf(p)));
        return users;

    }
}

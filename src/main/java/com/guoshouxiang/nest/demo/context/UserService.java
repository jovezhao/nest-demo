package com.guoshouxiang.nest.demo.context;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guoshouxiang.nest.context.event.EventBus;
import com.guoshouxiang.nest.context.loader.ConstructEntityLoader;
import com.guoshouxiang.nest.context.loader.EntityLoader;
import com.guoshouxiang.nest.context.loader.RepositoryEntityLoader;
import com.guoshouxiang.nest.context.model.StringIdentifier;
import com.guoshouxiang.nest.spring.AppService;
import com.guoshouxiang.nest.demo.context.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@AppService
public class UserService {
    @Autowired
    private EventBus eventBus;

    public void create(String userName, String pwd) {
        EntityLoader<User> entityLoader = new ConstructEntityLoader<>(User.class);
        User user = entityLoader.create(StringIdentifier.valueOf(UUID.randomUUID().toString()));
        user.init(userName, pwd);

    }

    public User get(String id) {
        EntityLoader<User> entityLoader = new RepositoryEntityLoader<>(User.class);
        User user = entityLoader.create(StringIdentifier.valueOf(id));
        return user;
    }

    @Autowired
    private QueryForUser queryForUser;

    public PageList<User> query() {
        PageList<User> list = queryForUser.getUserList(0, 5);
        return list;
    }
}

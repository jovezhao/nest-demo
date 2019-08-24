package com.guoshouxiang.nest.demo.application;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guohuoxiang.nest.mybatis.pagination.PageParames;
import com.guoshouxiang.nest.context.EntityFactory;
import com.guoshouxiang.nest.context.event.BaseEvent;
import com.guoshouxiang.nest.context.event.EventBus;
import com.guoshouxiang.nest.context.event.EventData;
import com.guoshouxiang.nest.context.loader.ConstructEntityLoader;
import com.guoshouxiang.nest.context.loader.EntityLoader;
import com.guoshouxiang.nest.context.loader.RepositoryEntityLoader;
import com.guoshouxiang.nest.context.model.StringIdentifier;
import com.guoshouxiang.nest.demo.domain.queries.UserQuery;
import com.guoshouxiang.nest.spring.AppService;
import com.guoshouxiang.nest.demo.domain.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@AppService
public class UserServiceImpl implements UserService {
    @Autowired
    private EventBus eventBus;

    @Autowired
    private Mapper beanMapper;

    public void create(String userName, String pwd) {
        EntityLoader<User> entityLoader = new ConstructEntityLoader<>(User.class);
        User user = entityLoader.create(StringIdentifier.valueOf(UUID.randomUUID().toString()));
        user.init(userName, pwd);

    }

    public UserDto get(String id) {
        EntityLoader<User> entityLoader = new RepositoryEntityLoader<>(User.class);
        User user = entityLoader.create(StringIdentifier.valueOf(id));
        UserDto userDto = beanMapper.map(user, UserDto.class);
        return userDto;
    }


    @Autowired
    private UserQuery userQuery;

    public PageList<UserDto> query() {
        PageList<User> list = userQuery.getList(PageParames.create(0, 5));
        PageList<UserDto> userDtos = list.mapPageList(p -> beanMapper.map(p, UserDto.class));
        return userDtos;
    }

    @Override
    public void changePassword(String id, String pwd) {
        User user = EntityFactory.load(User.class, StringIdentifier.valueOf(id));

        PasswordChangedEventData eventData = new PasswordChangedEventData();
        eventData.setNewPassword(pwd);
        eventData.setUserId(id);
        eventData.setOldPassword(user.getPassword());


        user.changePassword(pwd);


        eventBus.publish(eventData);
    }

}


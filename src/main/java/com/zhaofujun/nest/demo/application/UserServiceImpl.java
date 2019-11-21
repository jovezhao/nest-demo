package com.zhaofujun.nest.demo.application;


import com.zhaofujun.nest.context.model.StringIdentifier;
import com.zhaofujun.nest.core.EntityFactory;
import com.zhaofujun.nest.core.EntityLoader;
import com.zhaofujun.nest.core.EventBus;
import com.zhaofujun.nest.demo.domain.queries.UserQuery;
import com.zhaofujun.nest.demo.domain.User;
import com.zhaofujun.nest.mybatis.paging.PageList;
import com.zhaofujun.nest.mybatis.paging.PageParames;
import com.zhaofujun.nest.spring.AppService;
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
        User user = EntityFactory.create(User.class, StringIdentifier.valueOf(UUID.randomUUID().toString()));
        user.init(userName, pwd);

    }

    public UserDto get(String id) {
        User user = EntityFactory.load(User.class,StringIdentifier.valueOf(id));
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


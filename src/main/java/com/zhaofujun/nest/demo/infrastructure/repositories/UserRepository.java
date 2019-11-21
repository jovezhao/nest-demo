package com.zhaofujun.nest.demo.infrastructure.repositories;

import com.zhaofujun.nest.core.EntityLoader;
import com.zhaofujun.nest.core.Identifier;
import com.zhaofujun.nest.core.Repository;
import com.zhaofujun.nest.demo.domain.User;
import com.zhaofujun.nest.demo.infrastructure.dao.UserDmo;
import com.zhaofujun.nest.demo.infrastructure.dao.mapper.UserDmoMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements Repository<User> {

    @Autowired
    private UserDmoMapper userDmoMapper;
    @Autowired
    private Mapper beanMapper;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getEntityById(Identifier identifier, EntityLoader<User> entityLoader) {

        UserDmo userDmo = userDmoMapper.selectByPrimaryKey(identifier.toValue());

        User user = entityLoader.create(identifier);

        beanMapper.map(userDmo, user);
        return user;
    }

    @Override
    public void insert(User user) {
        UserDmo record = new UserDmo();
        record.setId(user.getId().toValue());
        record.setPassword(user.getPassword());
        record.setUsername(user.getUsername());
        userDmoMapper.insert(record);
    }

    @Override
    public void update(User user) {
        UserDmo record = new UserDmo();
        record.setId(user.getId().toValue());
        record.setPassword(user.getPassword());
        record.setUsername(user.getUsername());
        userDmoMapper.updateByPrimaryKey(record);
    }

    @Override
    public void delete(User user) {
        userDmoMapper.deleteByPrimaryKey(user.getId().getId());

    }


}

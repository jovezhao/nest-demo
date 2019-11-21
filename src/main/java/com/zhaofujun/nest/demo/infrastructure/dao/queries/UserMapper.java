package com.zhaofujun.nest.demo.infrastructure.dao.queries;

import com.zhaofujun.nest.mybatis.paging.PageList;
import com.zhaofujun.nest.mybatis.paging.PageParames;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id from user")
    PageList<String> getList(PageParames pageParames);
}

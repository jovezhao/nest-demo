package com.guoshouxiang.nest.demo.infrastructure.dao.queries;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guohuoxiang.nest.mybatis.pagination.PageParames;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id from user")
    PageList<String> getList(PageParames pageParames);
}

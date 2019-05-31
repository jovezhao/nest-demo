package com.guoshouxiang.nest.demo.infrastructure.dao.queries;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guohuoxiang.nest.mybatis.pagination.PageParames;
import com.guoshouxiang.nest.demo.context.queries.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends UserQuery {
    @Select("select id from user")
    PageList<String> getList(PageParames pageParames);
}

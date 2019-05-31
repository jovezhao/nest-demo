package com.guoshouxiang.nest.demo.context.queries;

import com.guohuoxiang.nest.mybatis.pagination.PageList;
import com.guohuoxiang.nest.mybatis.pagination.PageParames;

public interface UserQuery {

    PageList<String> getList(PageParames pageParames);
}

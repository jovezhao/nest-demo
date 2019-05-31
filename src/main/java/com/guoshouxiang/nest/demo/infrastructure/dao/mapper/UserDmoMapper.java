package com.guoshouxiang.nest.demo.infrastructure.dao.mapper;

import com.guoshouxiang.nest.demo.infrastructure.dao.UserDmo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserDmoMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into user (id, username, ",
        "password)",
        "values (#{id,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    int insert(UserDmo record);

    @InsertProvider(type=UserDmoSqlProvider.class, method="insertSelective")
    int insertSelective(UserDmo record);

    @Select({
        "select",
        "id, username, password",
        "from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    UserDmo selectByPrimaryKey(String id);

    @UpdateProvider(type=UserDmoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserDmo record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserDmo record);
}
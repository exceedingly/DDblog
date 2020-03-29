package com.mcd.mapper;

import com.mcd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Mapper
public interface UserMapper {
    @Select("select count(*) from user where username = #{username} and password = #{password}")
    int selUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}

package com.mcd.mapper;

import com.mcd.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User selUser(User user);

    @Select("select * from user where account_id = #{CreatorId}")
    User findByCreatorId(@Param("CreatorId") String CreatorId);
    @Select("select * from user where token = #{Token}")
    User findByToken(@Param("Token") String Token);

    @Update("UPDATE user SET token = #{token} WHERE token = #{id}")
    void updTokenByToken(String id,String token);
}

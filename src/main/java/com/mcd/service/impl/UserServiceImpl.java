package com.mcd.service.impl;

import com.mcd.mapper.UserMapper;
import com.mcd.pojo.User;
import com.mcd.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Data
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int selUserByUser(User user) {
        System.out.println(user);
        int i = userMapper.selUser(user);

        return i;
    }
}

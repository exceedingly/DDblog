package com.mcd.service;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

        public User selUserByUser(User user) {

            User u = userMapper.selUser(user);
            System.out.println(u);
            return u;

        }
    public void ChangeTokenByUserLogin(String token,String newStoken){
            userMapper.updTokenByToken(token,newStoken);
    }

}

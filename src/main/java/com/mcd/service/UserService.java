package com.mcd.service;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

        public User selUserByUser(User user) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPasswordLike(user.getPassword()).andUsernameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()==0){
                System.out.println("UserService 25");
            }

            return users.get(0);

        }
    public void ChangeTokenByUserLogin(String token,String newStoken){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andTokenEqualTo(token);
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()!=0){
                //根据旧token查找出对象
                User oldUser = users.get(0);
                users.get(0).setToken(newStoken);
            }
    }
    public List<User> selAllUser(){
            UserExample userExample = new UserExample();

        List<User> users = userMapper.selectByExample(userExample);

        return users;
    }

}

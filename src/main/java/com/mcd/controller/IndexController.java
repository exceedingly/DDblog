package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MaoChenDong on 2020/3/22.
 */
@EnableAutoConfiguration
@RestController
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @GetMapping("/")
    public String idnex(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo("admin");
        List<User> users = userMapper.selectByExample(userExample);
        System.out.println(users);

        return "123456";
    }










}

package com.mcd.controller;

import com.alibaba.fastjson.JSONArray;
import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogContextControlltr {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/selAllUser")

    public  String serAllUserG(Model model) {



        return  "selAllUser";

    }

    @RequestMapping("/selAllUser")
    @ResponseBody
    public User serAllUserP() {
        User u=(User)userMapper.findByToken("1323086220");
        return u;
    }
}

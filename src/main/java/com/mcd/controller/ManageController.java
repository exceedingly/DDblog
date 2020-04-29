package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Controller()
public class ManageController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/manage")
    public String loginController(HttpServletRequest request,
                                  Model model,
                                  HttpServletResponse response) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    System.out.println(cookie.getValue());
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(cookie.getValue());

//                    user = userMapper.findByCreatorId(cookie.getValue());
                    List<User> users = userMapper.selectByExample(userExample);
                    System.out.println("my is user  " + user);
                    if (users.size()!=0) {
                        request.getSession().setAttribute("user", users.get(0));
                        user = users.get(0);
                    }
                    System.out.println(user);
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("usernull", "请登录");
            return "login";
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
//        User u = userMapper.findByCreatorId(user.getAccountIdt_id());

        if(users.size()!=0){
            model.addAttribute("user",users.get(0));
        }else{
            System.out.println("ManageController 方法 users.get(0) 是0  出现错误");
        }






        return "manage";
    }
}

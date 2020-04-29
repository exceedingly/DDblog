package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                    user = userMapper.findByToken(cookie.getValue());
                    System.out.println("my is user  " + user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
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

        User u = userMapper.findByToken("1323086220");
        model.addAttribute("user",u);




        return "manage";
    }
}

package com.mcd.controller;

import com.mcd.model.User;
import com.mcd.service.UserService;
import com.mcd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Controller
public class loginController {
    @Autowired
    UserService userService = new UserServiceImpl();

    @PostMapping("/login")
    public String loginController(HttpServletRequest request,
                                  Model model,
                                  HttpServletResponse response){

        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);



        String u = request.getParameter("u");
        String p = request.getParameter("p");
//        String token =  UUID.randomUUID().toString();
        System.out.println("u is"+u);
        System.out.println(p);
        User user = new User();
        user.setUsername(u);
        user.setPassword(p);
        try{
            int i = userService.selUserByUser(user);
            System.out.println("my is i:"+i);
            if(i>0){
                //此时登陆已经成功
                System.out.println("写入cookie值");
                response.addCookie(new Cookie("token","1323086220"));

            }else{
                System.out.println("数据库没有");
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return "redirect:/";
    }
}

package com.mcd.controller;

import com.mcd.model.User;
import com.mcd.service.UserService;
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
    UserService userService;


    @PostMapping("/login")
    public String loginController(HttpServletRequest request,
                                  Model model,
                                  HttpServletResponse response){

//        StringBuffer requestURL = request.getRequestURL();
//        System.out.println(requestURL);



        String u = request.getParameter("u");
        String p = request.getParameter("p");
//        String token =  UUID.randomUUID().toString();
        System.out.println("u is"+u);
        System.out.println(p);
        User user = new User();
        user.setUsername(u);
        user.setPassword(p);
        try{
            User newU = userService.selUserByUser(user);

            if(null !=newU){
                //此时登陆已经成功

                String token = UUID.randomUUID().toString();
               System.out.println("更新yoken"+newU.getToken());
                userService.ChangeTokenByUserLogin(newU.getToken(),token);
                System.out.println("更新结束"+token);

                response.addCookie(new Cookie("token",token));

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

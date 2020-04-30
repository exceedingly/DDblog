package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import com.mcd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaoChenDong on 2020/3/23.
 */
@Controller
public class loginController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


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
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andUsernameEqualTo(u).andPasswordEqualTo(p);
            List<User> users = userMapper.selectByExample(userExample);


            if(users.size()!=0){
                //此时登陆已经成功

                String token = UUID.randomUUID().toString();
                UserExample userExample1 = new UserExample();
                userExample1.createCriteria().andIdEqualTo(users.get(0).getId());
                List<User> users1 = userMapper.selectByExample(userExample1);
                // 根据id查出user  根据user 修改token
                User selByIdUserResult = users1.get(0);
                selByIdUserResult.setToken(token);
                userMapper.updateByExample(selByIdUserResult,userExample1);



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

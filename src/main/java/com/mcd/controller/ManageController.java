package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        //设置浏览量
                FileReader fr=null;
                 BufferedReader br= null;


              try{

                  fr = new FileReader("/home/mcd/nums.txt");
        //          fr = new FileReader("C:/mycore/ideaProject2020/3/0320/src/main/resources/static/nums/nums.txt");
                  br=new BufferedReader(fr);

                  String count = (String)br.readLine();

                  String i  = Integer.toString(Integer.parseInt(count)+1);

                  model.addAttribute("fw",i);

              }catch (Exception e){
                  System.out.println("读文件异常");
                 // e.printStackTrace();
              }finally {
                  try {
                      if(null!=br)
                          br.close();
                  } catch (IOException e) {
                      System.out.println("关闭selAllJson IO流异常");
                  }
                  try {
                      if(null!=fr)
                          fr.close();
                  } catch (IOException e) {
                      System.out.println("关闭selAllJson IO流异常");
                  }
              }




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
        }else {

            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(user.getId());
            List<User> users = userMapper.selectByExample(userExample);
//        User u = userMapper.findByCreatorId(user.getAccountIdt_id());

            if (users.size() != 0) {
                model.addAttribute("user", users.get(0));
            } else {
                System.out.println("ManageController 方法 users.get(0) 是0  出现错误");
            }


        }



        return "manage";
    }

}

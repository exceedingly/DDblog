package com.mcd.controller;

import com.mcd.mapper.UserMapper;
import com.mcd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by MaoChenDong on 2020/3/22.
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

  @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
      System.out.println("处理/逻辑");
      Cookie[] cookies = request.getCookies();
      if (null != cookies) {
          for (Cookie cookie : cookies) {
              if (cookie.getName().equals("token")) {
                  System.out.println(cookie.getValue());
                  User user = userMapper.findByToken(cookie.getValue());
                  System.out.println("my is user  "+ user);
                  request.getSession().setAttribute("user",user);
                  System.out.println(user);
                  break;
              }
          }
      }else{
          System.out.println("cookie is null");
      }
      FileReader fr=null;
      BufferedReader br= null;
      FileWriter fw = null;
      BufferedWriter bw = null;
      try{

          fr = new FileReader("/home/mcd/nums.txt");
          br=new BufferedReader(fr);

          String count = (String)br.readLine();
          System.out.println("my is ##################"+count);
          String i  = Integer.toString(Integer.parseInt(count)+1);
          model.addAttribute("message",i);
//          fw = new FileWriter("C:/mycore/ideaProject2020/3/0320/src/main/resources/static/nums/nums.txt",false);
          fw = new FileWriter("/home/mcd/nums.txt",false);
          bw = new BufferedWriter(fw);
          bw.write(i);
          bw.flush();
      }catch (Exception e){
          System.out.println("读文件异常");
          e.printStackTrace();
      }
      return "index";
  }

//
//        for(Cookie cookie:cookies){
//            if(cookie.getName().equals("token")){
//                String token=cookie.getValue();
//                try{
//                    User user=userMapper.findByToken(token);
//                    request.getSession().setAttribute("user",user);
//                    System.out.println("user+"+user);
//                }catch (Exception e){
//                    System.out.println("no execute");
//                    return "index";
//                }
//
//
//                break;
//            }
//        }





    @GetMapping(value="/test")
    public String aaa(Model model) {


        return "nav_bottom_left_text";
    }


    @GetMapping("/mcd")
    public String getMassage(Model model){

        model.addAttribute("message","my is mass age");

        return "index";
    }

//    @GetMapping("/error")
//    public String error(Model model){
//
//
//        return "error";
//    }
    @GetMapping("/login")
    public String Login(Model model){
        model.addAttribute("message","my is mass age");
        return "login";
    }
    @GetMapping("/nav_bottom_left_text")
    public String nav_bottom_left_text(Model model){
        model.addAttribute("message","my is mass age");
        return "nav_bottom_left_text";
    }

}

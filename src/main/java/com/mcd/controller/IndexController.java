package com.mcd.controller;

import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by MaoChenDong on 2020/3/22.
 */
@EnableAutoConfiguration
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private QuestionService questionService;



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
//          fr = new FileReader("C:/mycore/ideaProject2020/3/0320/src/main/resources/static/nums/nums.txt");
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
         // e.printStackTrace();
      }finally {
          try {
              if(null!=bw)
                  bw.close();
          } catch (IOException e) {
              System.out.println("关闭selAllJson IO流异常");
          }
          try {
              if(null!=fw)
                  fw.close();
          } catch (IOException e) {
              System.out.println("关闭selAllJson IO流异常");
          }
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

      List<QuestionDTO> questionDTOList = questionService.list();
      model.addAttribute("questions",questionDTOList);

      return "index";
  }






    @GetMapping(value="/test")
    public String aaa(Model model) {


        return "nav_bottom_left_text";
    }


    @GetMapping(value="/zh")
    public String zh(Model model) {


        return "zh";
    }


    @GetMapping("/mcd")
    public String getMassage(Model model){
      try{

              User user = (User)userMapper.findByToken("1323086220");

              System.out.println(user);
              model.addAttribute("message",user);

      }catch (Exception e){
          System.out.println("读取远程数据库失败");
      }



        return "mcd";
    }

//    @GetMapping("/error")
//    public String error(Model model){
//
//
//        return "error";
//    }
    @GetMapping("/login")
    public String Login(Model model
                       ){
        model.addAttribute("message","my is mass age");

        return "login";
    }
    @GetMapping("/nav_bottom_left_text")
    public String nav_bottom_left_text(Model model){
        model.addAttribute("message","my is mass age");
        return "nav_bottom_left_text";
    }







}

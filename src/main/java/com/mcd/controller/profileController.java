package com.mcd.controller;

import com.mcd.dto.PageInfoDTO;
import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profilr(@PathVariable(name="action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size
                          ){

        User user=null;
        //登陆验证
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    System.out.println(cookie.getValue());
                    user= userMapper.findByToken(cookie.getValue());
                    System.out.println("my is user  "+ user);
                    request.getSession().setAttribute("user",user);
                    System.out.println(user);
                    break;
                }
            }
        }

        if (user == null) {

            return "redirect:/";
        }





        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问 ");
        }else if("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复 ");
        }


        PageInfoDTO pageInfoDTO = questionService.list(user.getAccount_id(), page, size);
        model.addAttribute("pageInfoDTO",pageInfoDTO);
        model.addAttribute("questions",pageInfoDTO.getQuestion());


        return "profile";
    }
}

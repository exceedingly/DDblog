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
public class MyQuestionController {


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

        User user = (User)request.getSession().getAttribute("user");
        if(null ==user)
            return "login";







        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问 ");
        }else if("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复 ");
        }


        PageInfoDTO pageInfoDTO = questionService.listUserId(user.getToken(), page, size);
        System.out.println("pageInfoDTO +"+pageInfoDTO);
        model.addAttribute("pageInfoDTO",pageInfoDTO);
        model.addAttribute("questions",pageInfoDTO.getQuestion());


        return "myquestion";
    }
}

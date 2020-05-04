package com.mcd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.QuestionExample;
import com.mcd.model.User;

import com.mcd.service.QuestionService;
import com.mcd.service.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Controller
public class BlogContextControlltr {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;



    @RequestMapping("/selAllUser")
    public  String serAllUserG(Model model) {

        List<User> users = userService.selAllUser();
        model.addAttribute("users",users);
        return  "selAllUser";

    }


    @GetMapping("/selAllQuestion")

    public  String selAllQuestionG1(Model model,
                                   @RequestParam(name="size",defaultValue = "5") Integer size,
                                   @RequestParam(name="page",defaultValue = "1") Integer page,
                                   HttpServletResponse response
                                   ) {




        PageInfoDTO pageinfo = questionService.list(page,size);
        List<QuestionDTO> questions = pageinfo.getQuestion();

        model.addAttribute("pageinfo",pageinfo);

        model.addAttribute("questions",questions);
        return  "selAllQuestion";

    }
@GetMapping("/mpublish")
    public String mpublishG(HttpServletResponse response){
        return "mpublish";
}
    @PostMapping("/deleteBlog")
    public String  deleteBlogP(@RequestParam(name="delBlogId") Integer id,
                               Model model,
                               @RequestParam(name="size",defaultValue = "5") Integer size,
                               @RequestParam(name="page",defaultValue = "1") Integer page){
        System.out.println(id+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
       questionService.delete(id);
        PageInfoDTO pageinfo = questionService.list(page,size);
        List<QuestionDTO> questions = pageinfo.getQuestion();

        model.addAttribute("pageinfo",pageinfo);
        System.out.println(pageinfo);
        model.addAttribute("questions",questions);



        return "selAllQuestion";



    }


}

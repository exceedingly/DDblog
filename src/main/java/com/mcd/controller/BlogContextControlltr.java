package com.mcd.controller;

import com.alibaba.fastjson.JSONObject;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.User;

import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.List;


@Controller
public class BlogContextControlltr {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;



    @GetMapping("/selAllUser")

    public  String serAllUserG(Model model) {


        return  "selAllUser";

    }

    @GetMapping("/selAllQuestion")

    public  String selAllQuestionG(Model model,
                                   @RequestParam(name="page",defaultValue = "1") Integer page,
                                   @RequestParam(name="size",defaultValue = "5") Integer size
                                   ) {




        PageInfoDTO pageinfo = questionService.list(page,size);
        List<QuestionDTO> questions = pageinfo.getQuestion();

        model.addAttribute("pageinfo",pageinfo);
        System.out.println(pageinfo);
        model.addAttribute("questions",questions);
        System.out.println(questions);


        return  "selAllQuestion";

    }



}

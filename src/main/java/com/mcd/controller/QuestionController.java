package com.mcd.controller;

import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
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
import javax.xml.ws.Action;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;
    @GetMapping("/question/{id}")
    public String  question(@RequestParam(name = "id",defaultValue = "1") Integer id,
                            Model model,
                            HttpServletRequest request) {






        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}

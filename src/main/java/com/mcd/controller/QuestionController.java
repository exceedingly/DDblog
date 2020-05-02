package com.mcd.controller;

import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.UserMapper;
import com.mcd.model.User;
import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;
    @GetMapping("/question/{id}")
    public String  question(@PathVariable(name="id") Integer id,
                            Model model,
                            HttpServletRequest request) {




        QuestionDTO questionDTO=questionService.getById(id);


        //设置 不是同一个作者 只读

        User u = (User)request.getSession().getAttribute("user");
        if(u !=null){
            Integer id1 = questionDTO.getUser().getId();
            Integer id2 = u.getId();
            if(id1.equals(id2)){


                System.out.println("id == flag =1 ");
                model.addAttribute("flag","1");
            }
            else{
                System.out.println("id != flag =0");

                model.addAttribute("flag","0");
            }

        }


        model.addAttribute("user",questionDTO.getUser());
        model.addAttribute("question",questionDTO);
        return "question";
    }
}

package com.mcd.controller;

import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller()
public class PublicController {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;


    @GetMapping("/publish")
    //get渲染页面
    public String publish( HttpServletRequest request){

                    User user = (User)request.getSession().getAttribute("user");
                   if(null ==user)
                    return "login";

        return "publish";
    }
    @PostMapping("/publish")
    //po执行请求
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model){

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("usernull","请登录");
            return "publish";
        }
        if(title == null || title.equals("")){
            model.addAttribute("titlenull","标题不能为空");
            return "publish";
        }
        if(description == null || description.equals("")){
            model.addAttribute("descriptionnull","内容不能为空");
            return "publish";
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("tagnull","标签不能为空");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);

        question.setCreator(user.getToken());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question);
        model.addAttribute("insertSuccess","200");


        return "redirect:/";
    }
}

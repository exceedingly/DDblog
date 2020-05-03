package com.mcd.controller;

import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.QuestionExample;
import com.mcd.model.User;
import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller()
public class PublicController {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/publish/{id}")
    //get渲染页面
    public String edit(@PathVariable(name="id") Integer id,
                       Model model) {

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);
        Question question = questions.get(0);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("id", question.getId());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        return "publish";
    }


    @GetMapping("/publish")
    //get渲染页面
    public String publish(HttpServletRequest request) {

        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            return "login";
        }
        return "publish";
    }

    @PostMapping("/publish")
    //po执行请求
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
            ) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("usernull", "请登录");
            return "publish";
        }
        if (title == null || title.equals("")) {
            model.addAttribute("titlenull", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("descriptionnull", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("tagnull", "标签不能为空");
            return "publish";
        }


        Question question = new Question();


        if( id ==null){
            question.setId(id);
            //插入
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());

            questionMapper.insert(question);
            model.addAttribute("insertSuccess", "200");

        }else{
           // 更新



            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(id);
            List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);
            questions.get(0).setTag(tag);
            questions.get(0).setTitle(title);
            questions.get(0).setDescription(description);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!+id "+id);
            questionMapper.updateByExampleWithBLOBs(questions.get(0),questionExample);

            model.addAttribute("insertSuccess", "200");
        }

        String result = "/question/"+id;

        return "redirect:/";
    }
}

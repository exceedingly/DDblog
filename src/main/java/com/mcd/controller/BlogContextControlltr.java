package com.mcd.controller;

import com.alibaba.fastjson.JSONObject;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.List;


@Controller
public class BlogContextControlltr {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/selAllUser")

    public  String serAllUserG(Model model) {
        User u = userMapper.findByToken("1323086220");
        String jsonPre ="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": null,\n" +
                "\n" +
                "  \"data\": [\n" ;

        String jsonNext = "]\n" +
                "}";
        String json = JSONObject.toJSONString(u);
        String tranjson=jsonPre+json+jsonNext;
        System.out.println(tranjson);
        FileReader fr=null;
        BufferedReader br= null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{

            fr = new FileReader("C:/mycore/ideaProject2020/3/0320/src/main/resources/static/json/selAllBlogContext.json");
            br=new BufferedReader(fr);



            fw = new FileWriter("C:/mycore/ideaProject2020/3/0320/src/main/resources/static/json/selAllBlogContext.json",false);
            bw = new BufferedWriter(fw);
            bw.write(tranjson);
            bw.flush();
        }catch (Exception e){
            System.out.println("读文件异常");
            // e.printStackTrace();
        }

        return  "selAllUser";

    }

    @GetMapping("/selAllQuestion")

    public  String selAllQuestionG(Model model) {
        List<Question> questions = questionMapper.sellAllQuestion();
        model.addAttribute("questions",questions);


        return  "selAllQuestion";

    }


}

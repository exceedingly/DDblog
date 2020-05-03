package com.mcd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.User;

import com.mcd.service.QuestionService;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
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
//
//    @GetMapping("/selAllQuestion/{page}")
//
//    public  String selAllQuestionG(Model model,
//                                   @PathVariable(name="page") Integer page,
//
//                                   @RequestParam(name="size",defaultValue = "5") Integer size,
//                                   HttpServletResponse response
//                                   ) {
//
//
//
//
//        PageInfoDTO pageinfo = questionService.list(page,size);
//        List<QuestionDTO> questions = pageinfo.getQuestion();
//
//        model.addAttribute("pageinfo",pageinfo);
//        System.out.println(pageinfo);
//        model.addAttribute("questions",questions);
//        System.out.println(questions);
//
//
//
//
////        {
//////  "code": 0,
//////  "msg": "",
//////  "count": 1000,
//////  "data": [
//
//
////       String json = JSONArray.toJSONString(questions);
////        response.setCharacterEncoding("utf-8");
////        response.setContentType("application/json; charset=utf-8");
////        PrintWriter writer = null;
////        try {
////            writer = response.getWriter();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        String newJson = "{\n" +
////                "  \"code\": 0,\n" +
////                "  \"msg\": null,\n" +
////                "\n" +
////                "  \"data\":["+json+"]}";
////        writer.write(newJson);
//
//        return  "selAllQuestion";
//
//    }

    @GetMapping("/selAllQuestion")

    public  String selAllQuestionG1(Model model,
                                   @RequestParam(name="size",defaultValue = "5") Integer size,
                                   @RequestParam(name="page",defaultValue = "1") Integer page,
                                   HttpServletResponse response
                                   ) {




        PageInfoDTO pageinfo = questionService.list(page,size);
        List<QuestionDTO> questions = pageinfo.getQuestion();

        model.addAttribute("pageinfo",pageinfo);
        System.out.println(pageinfo);
        model.addAttribute("questions",questions);
        System.out.println(questions);




//        {
////  "code": 0,
////  "msg": "",
////  "count": 1000,
////  "data": [


//       String json = JSONArray.toJSONString(questions);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String newJson = "{\n" +
//                "  \"code\": 0,\n" +
//                "  \"msg\": null,\n" +
//                "\n" +
//                "  \"data\":["+json+"]}";
//        writer.write(newJson);

        return  "selAllQuestion";

    }
@GetMapping("/mmm")
    public void mmm(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");



}


}

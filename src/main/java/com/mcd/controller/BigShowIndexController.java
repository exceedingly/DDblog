package com.mcd.controller;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BigShowIndexController {
    @Autowired
    QuestionService questionService;





    @GetMapping("/changeBigById")
    public String changeBigByIdP(
            Model model,
            @RequestParam(name="size",defaultValue = "5") Integer size,
            @RequestParam(name="page",defaultValue = "1") Integer page,
            @RequestParam(name="bigshowid",defaultValue = "0") Integer bigshowid,
            HttpServletResponse response){
        if(bigshowid==0) {


            PageInfoDTO pageinfo = questionService.list(page, size);
            List<QuestionDTO> questions = pageinfo.getQuestion();
            model.addAttribute("pageinfo", pageinfo);
            model.addAttribute("questions", questions);
            model.addAttribute("bigshowid", bigshowid);
            return "BigShow";
        }else{

            QuestionDTO questionDTO = questionService.getById(bigshowid);
            model.addAttribute("questionDTO",questionDTO);
            model.addAttribute("bigshowid",bigshowid);


            return "BigShow";
        }


    }
}

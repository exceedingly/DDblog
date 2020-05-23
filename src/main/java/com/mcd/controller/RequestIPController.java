package com.mcd.controller;

import com.mcd.service.RequestIPService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestIPController {

    @Autowired
    RequestIPService requestIPService;

    @GetMapping("/ipadd")
    public String addIp(HttpServletRequest request){
        System.out.println("333333333333333333333");
        System.out.println(request.getRemoteAddr());
        String str01 = request.getRemoteAddr();
        if ("192.168.1.101".equals(str01)){
            return "error";
        }
        return "ipsuccess";
    }






}

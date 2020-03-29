package com.mcd.service.impl;

import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by MaoChenDong on 2020/3/29.
 */
@Service
public class numServiceImpl extends HttpServlet implements numsService  {
    public numServiceImpl() {

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        String path = "/nums/nums.txt";
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr= new FileReader(path);
            br=new BufferedReader(fr);
            String nums=(String)br.readLine();
            System.out.println("############################"+nums);

        }catch (Exception e){
            System.out.println("计数器读取错误");
        }finally {
            try{
                br.close();
            }catch (Exception e){
                System.out.println("br 关闭失败");
            }
            try{
                fr.close();
            }catch (Exception e){
                System.out.println("fr 关闭失败");
            }
        }
    }
}

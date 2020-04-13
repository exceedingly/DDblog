package com.mcd;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class testZH {
    @Test
    public void zh(){
        try {

            // 封装请求参数
            String json = "{\"include\": \"data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled,is_recognized;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics\",\n" +
                    "\"limit\": 20,\n" + //每列显示的个数
                    "\"offset\": 15,\n" + //页数
                    "\"platform\": \"desktop\",\n" +
                    "\"sort_by\": \"default\"}";
            JSONObject object = JSONObject.parseObject(json);

            // 使用jsoup对url参数发起请求
            Connection connect =  Jsoup.connect("https://www.zhihu.com/question/308204192/answer/1120772009");
//            Connection connect =  Jsoup.connect("https://www.zhihu.com/api/v4/questions/22913650/answers");

            // 将封装的参数放至jsoup 作为发送请求的参数  例：?limit: 20&offset: 15
            for (String key : object.keySet()) {
                connect.data(key,object.get(key).toString());
            }

            // 发起请求并接受响应
            Connection.Response response = connect
                    // 防止 UnsupportedMimeTypeException 异常
                    .ignoreContentType(true)
                    // 伪装
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                    .execute();

            // 解析响应体
            JSONObject responseJson = JSONObject.parseObject(response.body());
            JSONArray dataArray = JSONArray.parseArray(responseJson.get("data").toString());
            System.out.println(dataArray);
            /**
             * 文件io操作
             */

            // 创建 C:\Users\Admin\Desktop 文件夹
            File file = new File("C:\\Users\\Admin\\Desktop");
            if (!file.exists()) {
                file.mkdirs();
            }

            // 创建 C:\Users\Admin\Desktop\zh.txt 文件
            File ZH = new File("D:/zh.txt");
            if (!ZH.isFile()){
                ZH.createNewFile();
            }

            // 文件输出流&高效输出流  用于将数据写入到指定文件中
            FileWriter fileWriter = new FileWriter(ZH,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 从返回的响应体中过滤想要的数据并写入到文件中
            for (Object data : dataArray) {
                JSONObject dataJO = JSONObject.parseObject(data.toString());
                JSONObject author = JSONObject.parseObject(dataJO.get("author").toString());
                Object authorName = author.get("name");
                Object excerpt = dataJO.get("content");
                Object voteup_count = dataJO.get("voteup_count");
                // 将数据写入缓存区
                bufferedWriter.write(authorName+"("+voteup_count+"人赞同):");
                bufferedWriter.write("\n");
                bufferedWriter.write(excerpt.toString());
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
            }

            // 刷新   注：将缓存区的数据刷新到文件中
            bufferedWriter.flush();

            // 关流   注：节约资源
            fileWriter.close();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

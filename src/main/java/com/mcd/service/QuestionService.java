package com.mcd.service;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfoDTO list(Integer page, Integer size){
        //去数据库拿数据
        Integer offset = size*(page-1);


        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //那好数据 给dto
        PageInfoDTO pageInfoDTO = new PageInfoDTO();

        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()!=0){
                questionDTO.setUser(users.get(0));
            }else{
                System.out.println("QuestionService 46 error");
            }

            questionDTOS.add(questionDTO);

        }
        //设置 pageinfo DTO内容
        pageInfoDTO.setQuestion(questionDTOS);
        //数据总数
        Integer totalCount = questionMapper.count();
        //给你参数 你给我算 加工一下DTO主要是页面 页码现实问题
        pageInfoDTO.setPagination(totalCount,page,size);
        //dto加工完成

        return pageInfoDTO;


    }


    public PageInfoDTO listUserId(String userId, Integer page, Integer size) {
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        pageInfoDTO.setPagination(totalCount,page,size);
        if(page>pageInfoDTO.getPage()){
            page=pageInfoDTO.getPage();
        }
        if(page<1){
            page=1;
        }



        Integer offset = size*(page-1);


        List<Question> questions = questionMapper.listUserId(userId,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //那好数据 给dto


        for(Question question:questions){




            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);


            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size()!=0){

                questionDTO.setUser(users.get(0));
            }else{
                System.out.println("QuestionService 104");
            }


            questionDTOS.add(questionDTO);

        }
        //设置 pageinfo DTO内容
        pageInfoDTO.setQuestion(questionDTOS);
//        //数据总数
//        Integer totalCount = questionMapper.count();
//        //给你参数 你给我算 加工一下DTO主要是页面 页码现实问题
//        pageInfoDTO.setPagination(totalCount,page,size);
//        //dto加工完成

        return pageInfoDTO;

    }

    public QuestionDTO getById(Integer id) {

        QuestionDTO questionDTO = new QuestionDTO();


        Question question=questionMapper.getById(id);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()!=0){
            questionDTO.setUser(users.get(0));
        }else{
            System.out.println("QuestionService 113行出错");
        }




        BeanUtils.copyProperties(question,questionDTO);

        return questionDTO;
    }




    public void createOrUpdate(Question question){
        if(question.getId() == null){

            //是新建
            System.out.println("新建问题");
            questionMapper.create(question);
        }else{
            System.out.println("修改");

            question.setGmt_modified(question.getGmt_create());
            questionMapper.update(question);
        }
    }
}

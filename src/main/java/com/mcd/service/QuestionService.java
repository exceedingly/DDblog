package com.mcd.service;

import com.mcd.dto.PageInfoDTO;
import com.mcd.dto.QuestionDTO;
import com.mcd.mapper.QuestionMapper;
import com.mcd.mapper.UserMapper;
import com.mcd.model.Question;
import com.mcd.model.QuestionExample;
import com.mcd.model.User;
import com.mcd.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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
//        selectByExampleWithBLOBs
//        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //那好数据 给dto
        PageInfoDTO pageInfoDTO = new PageInfoDTO();

        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreator());
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

//        Integer totalCount = questionMapper.count();
//        //给你参数 你给我算 加工一下DTO主要是页面 页码现实问题
        int totalCount = (int)questionMapper.countByExample(new QuestionExample());
        pageInfoDTO.setPagination(totalCount,page,size);
        //dto加工完成

        return pageInfoDTO;


    }


    public PageInfoDTO listUserId(Integer userId, Integer page, Integer size) {
        PageInfoDTO pageInfoDTO = new PageInfoDTO();

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        questionMapper.countByExample(new QuestionExample());
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        pageInfoDTO.setPagination(totalCount,page,size);
        if(page>pageInfoDTO.getPage()){
            page=pageInfoDTO.getPage();
        }
        if(page<1){
            page=1;
        }



        Integer offset = size*(page-1);



           QuestionExample questionExample1= new QuestionExample();
           questionExample1.createCriteria()
                   .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample1, new RowBounds(offset, size));


        List<QuestionDTO> questionDTOS = new ArrayList<>();

        //那好数据 给dto


        for(Question question:questions){




            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);


            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreator());
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
        //数据总数


        QuestionExample questionExample2 = new QuestionExample();
       questionExample2.createCriteria();
        questionMapper.selectByExample(questionExample2);
        long l = questionMapper.countByExample(questionExample2);
        int totalCount2 = (int)l;
        //给你参数 你给我算 加工一下DTO主要是页面 页码现实问题


        pageInfoDTO.setPagination(totalCount2,page,size);
        //dto加工完成

        return pageInfoDTO;

    }

    public QuestionDTO getById(Integer id) {

        QuestionDTO questionDTO = new QuestionDTO();

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);

        Question question = null;
        if(questions.size()!=0){
            question = questions.get(0);
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()!=0){
            questionDTO.setUser(users.get(0));
        }else{
            System.out.println("QuestionService 113行出错");
        }



        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setDescription(question.getDescription());
        return questionDTO;
    }




    public void createOrUpdate(Question question){
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);


        }else{
            System.out.println("修改");
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }
    }

    public void delete(Integer id){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(id);
        questionMapper.deleteByExample(questionExample);
    }


}

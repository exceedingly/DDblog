//package com.mcd.service;
//
//import com.mcd.dto.LafterPageInfoManageDTO;
//import com.mcd.mapper.QuestionMapper;
//import com.mcd.mapper.UserMapper;
//import com.mcd.model.Question;
//import org.hibernate.validator.constraints.URL;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class AfterManageAllContextService {
//
//    @Autowired
//    QuestionMapper questionMapper;
//
//
//
//
//    public LafterPageInfoManageDTO toDTO(Integer page, Integer size) {
//        //去数据库拿数据
//        Integer offset = size*(page-1);
//        List<Question> questionList = questionMapper.list(offset, page);
//
//        //计算总数量
//        Integer count = questionMapper.count();
//
//
//        //每一页数据 给一个dto
//        LafterPageInfoManageDTO lafterPageInfoManageDTO = new LafterPageInfoManageDTO();
//        lafterPageInfoManageDTO.setQuestionList(questionList);
//        lafterPageInfoManageDTO.list(count,page,size);
//
//
//        return lafterPageInfoManageDTO;
//
//
//    }
//}

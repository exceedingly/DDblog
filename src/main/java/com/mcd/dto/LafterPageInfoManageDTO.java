//package com.mcd.dto;
//
//import com.mcd.model.Question;
//
//import java.util.List;
//
//public class LafterPageInfoManageDTO {
//    private List<Question> questionList;
//    private Integer page;
//
//    public List<Question> getQuestionList() {
//        return questionList;
//    }
//
//    public void setQuestionList(List<Question> questionList) {
//        this.questionList = questionList;
//    }
//
//    public void list(Integer totalCount,Integer page, Integer size) {
//        this.page=page;
//
//
//    //总共把数据分多少页 totalpage
//        Integer totalPage = 0;
//        if (totalCount % size == 0) {
//            totalPage = totalCount / size;
//
//        } else {
//            totalPage = totalCount / size + 1;
//        }
//
//
//
//    }
//}

package com.mcd.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/*
 页面所有元素 在这个对象组装
 */

public class PageInfoDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFristPage;
    private boolean showNest;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public List<QuestionDTO> getQuestion() {
        return questions;
    }

    public void setQuestion(List<QuestionDTO> question) {
        this.questions = question;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFristPage() {
        return showFristPage;
    }

    public void setShowFristPage(boolean showFristPage) {
        this.showFristPage = showFristPage;
    }

    public boolean isShowNest() {
        return showNest;
    }

    public void setShowNest(boolean showNest) {
        this.showNest = showNest;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageInfoDTO{" +
                "questions=" + questions +
                ", showPrevious=" + showPrevious +
                ", showFristPage=" + showFristPage +
                ", showNest=" + showNest +
                ", showEndPage=" + showEndPage +
                ", page=" + page +
                ", pages=" + pages +
                '}';
    }

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.page=page;
        // totalCount/size;  11 1 5
        Integer totalPage = 0;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;

        } else {
            totalPage = totalCount / size + 1;
        }

            // 3 11 1 5
        pages.add(page);
        for (int i = 1; i <= totalPage; i++) {
            if (page - i > 0) {
                pages.add(0,page-i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }

        }


        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //是否显示上一页
        if (page == totalPage) {
            showNest = false;
        } else {
            showNest = true;
        }


        //是否展示第一页
        if (pages.contains(1)) {
            showFristPage = false;
        } else {
            showFristPage = true;
        }

//        是否显示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}

package com.mcd.mapper;

import com.mcd.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);
    @Select("Select * from question")
    public List<Question> sellAllQuestion();

    @Select("select * from question limit #{offset},#{size}")
    public List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    public List<Question> listUserId(@Param("userId") String userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(*) from question where creator = #{userId} ")
    Integer countByUserId(String userId);
}

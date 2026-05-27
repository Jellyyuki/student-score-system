package com.score.mapper;

import com.score.entity.Course;
import com.score.entity.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScoreMapper {
    @Select("SELECT * FROM score")
    List<Score> findAll();

    @Select("SELECT * FROM score WHERE id = #{id}")
    Score findById(Integer id);

    @Insert("INSERT INTO score(student_id, course_id, score) " +
            "VALUES(#{studentId}, #{courseId}, #{score})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Score score);

    @Update("UPDATE score SET student_id=#{studentId}, course_id=#{courseId}, score=#{score} WHERE id=#{id}")
    void update(Score score);

    @Delete("DELETE FROM score WHERE id = #{id}")
    void deleteById(Integer id);
}

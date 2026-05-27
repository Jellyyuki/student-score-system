package com.score.mapper;

import com.score.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("SELECT * FROM course")
    List<Course> findAll();

    @Select("SELECT * FROM course WHERE id = #{id}")
    Course findById(Integer id);

    @Insert("INSERT INTO course(course_no, course_name, credit) " +
            "VALUES(#{courseNo}, #{courseName}, #{credit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Course course);

    @Update("UPDATE course SET course_no=#{courseNo}, course_name=#{courseName}, credit=#{credit} WHERE id=#{id}")
    void update(Course course);

    @Delete("DELETE FROM course WHERE id = #{id}")
    void deleteById(Integer id);

    // 根据课程名称或课程编号模糊查询
    @Select("SELECT * FROM course WHERE course_name LIKE CONCAT('%', #{keyword}, '%') OR course_no LIKE CONCAT('%', #{keyword}, '%')")
    List<Course> searchByKeyword(String keyword);
}

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

    // 联查成绩列表（包含学生姓名、课程名称、学号、课程编号等）
    @Select("SELECT s.id, s.score, stu.name as studentName, stu.student_no as studentNo, " +
            "c.course_name as courseName, c.course_no as courseNo, stu.id as studentId, c.id as courseId " +
            "FROM score s " +
            "LEFT JOIN student stu ON s.student_id = stu.id " +
            "LEFT JOIN course c ON s.course_id = c.id")
    @Results(id = "scoreMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "score", property = "score"),
            @Result(column = "studentId", property = "studentId"),
            @Result(column = "courseId", property = "courseId"),
            @Result(column = "studentName", property = "studentName"),
            @Result(column = "studentNo", property = "studentNo"),
            @Result(column = "courseName", property = "courseName"),
            @Result(column = "courseNo", property = "courseNo")
    })
    List<Score> findAllWithInfo();

    // 根据关键字（学生姓名/学号/课程名称）搜索成绩
    @Select("SELECT s.id, s.score, stu.name as studentName, stu.student_no as studentNo, " +
            "c.course_name as courseName, c.course_no as courseNo, stu.id as studentId, c.id as courseId " +
            "FROM score s " +
            "LEFT JOIN student stu ON s.student_id = stu.id " +
            "LEFT JOIN course c ON s.course_id = c.id " +
            "WHERE stu.name LIKE CONCAT('%', #{keyword}, '%') " +
            "OR stu.student_no LIKE CONCAT('%', #{keyword}, '%') " +
            "OR c.course_name LIKE CONCAT('%', #{keyword}, '%')")
    List<Score> searchWithInfo(String keyword);

    // 检查是否已存在同一学生同一课程的成绩（用于避免重复）
    @Select("SELECT COUNT(*) FROM score WHERE student_id = #{studentId} AND course_id = #{courseId}")
    int countByStudentAndCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}

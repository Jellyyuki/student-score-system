package com.score.mapper;

import com.score.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student ORDER BY id DESC")
    List<Student> findAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(Integer id);

    @Insert("INSERT INTO student(student_no, name, gender, birth_date, class_name) " +
            "VALUES(#{studentNo}, #{name}, #{gender}, #{birthDate}, #{className})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    @Update("UPDATE student SET student_no=#{studentNo}, name=#{name}, gender=#{gender}, " +
            "birth_date=#{birthDate}, class_name=#{className} WHERE id=#{id}")
    void update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteById(Integer id);

    // 根据姓名或学号模糊查询
    @Select("SELECT * FROM student WHERE name LIKE CONCAT('%', #{keyword}, '%') OR student_no LIKE CONCAT('%', #{keyword}, '%') ORDER BY id DESC")
    List<Student> search(@Param("keyword") String keyword);

    // ========== 班级筛选相关方法 ==========

    // 按班级筛选
    @Select("SELECT * FROM student WHERE class_name = #{className} ORDER BY id DESC")
    List<Student> findByClass(@Param("className") String className);

    // 按班级和关键词搜索
    @Select("SELECT * FROM student WHERE class_name = #{className} AND " +
            "(name LIKE CONCAT('%', #{keyword}, '%') OR student_no LIKE CONCAT('%', #{keyword}, '%')) " +
            "ORDER BY id DESC")
    List<Student> searchByClass(@Param("keyword") String keyword, @Param("className") String className);

    // 获取所有班级列表（用于下拉框）
    @Select("SELECT DISTINCT class_name FROM student WHERE class_name IS NOT NULL AND class_name != '' ORDER BY class_name")
    List<String> findAllClasses();

    // ========== 学号校验方法 ==========

    // 检查学号是否已存在（排除指定ID）
    @Select("SELECT COUNT(*) FROM student WHERE student_no = #{studentNo} AND id != #{excludeId}")
    int countByStudentNo(@Param("studentNo") String studentNo, @Param("excludeId") Integer excludeId);

    // ========== 成绩汇总相关方法 ==========

    // 获取学生的所有成绩（包含课程信息）
    @Select("SELECT " +
            "c.id as courseId, " +
            "c.course_no as courseNo, " +
            "c.course_name as courseName, " +
            "c.credit as credit, " +
            "s.score as score " +
            "FROM score s " +
            "LEFT JOIN course c ON s.course_id = c.id " +
            "WHERE s.student_id = #{studentId} " +
            "ORDER BY c.id")
    List<com.score.entity.StudentScoreVO.CourseScore> getStudentCourseScores(@Param("studentId") Integer studentId);

    // 获取学生成绩汇总（总分、平均分、课程数）
    @Select("SELECT " +
            "COUNT(*) as courseCount, " +
            "SUM(score) as totalScore, " +
            "ROUND(AVG(score), 1) as avgScore " +
            "FROM score WHERE student_id = #{studentId}")
    Map<String, Object> getScoreSummary(@Param("studentId") Integer studentId);
}
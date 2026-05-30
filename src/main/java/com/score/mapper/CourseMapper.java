package com.score.mapper;

import com.score.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM course ORDER BY id DESC")
    List<Course> findAll();

    @Select("SELECT * FROM course WHERE id = #{id}")
    Course findById(Integer id);

    @Insert("INSERT INTO course(course_no, course_name, credit, status) VALUES(#{courseNo}, #{courseName}, #{credit}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Course course);

    @Update("UPDATE course SET course_no=#{courseNo}, course_name=#{courseName}, credit=#{credit}, status=#{status} WHERE id=#{id}")
    void update(Course course);

    @Delete("DELETE FROM course WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM course WHERE course_name LIKE CONCAT('%', #{keyword}, '%') OR course_no LIKE CONCAT('%', #{keyword}, '%')")
    List<Course> search(@Param("keyword") String keyword);

    @Select("SELECT * FROM course WHERE status = #{status}")
    List<Course> findByStatus(@Param("status") String status);

    // 统计课程选课人数
    @Select("SELECT c.*, COUNT(s.id) as studentCount " +
            "FROM course c " +
            "LEFT JOIN score s ON c.id = s.course_id " +
            "GROUP BY c.id, c.course_no, c.course_name, c.credit, c.status " +
            "ORDER BY c.id DESC")
    List<Course> findAllWithCount();

    @Select("SELECT c.*, COUNT(s.id) as studentCount " +
            "FROM course c " +
            "LEFT JOIN score s ON c.id = s.course_id " +
            "WHERE c.course_name LIKE CONCAT('%', #{keyword}, '%') OR c.course_no LIKE CONCAT('%', #{keyword}, '%') " +
            "GROUP BY c.id, c.course_no, c.course_name, c.credit, c.status " +
            "ORDER BY c.id DESC")
    List<Course> searchWithCount(@Param("keyword") String keyword);

    @Select("SELECT c.*, COUNT(s.id) as studentCount " +
            "FROM course c " +
            "LEFT JOIN score s ON c.id = s.course_id " +
            "WHERE c.status = #{status} " +
            "GROUP BY c.id, c.course_no, c.course_name, c.credit, c.status " +
            "ORDER BY c.id DESC")
    List<Course> findByStatusWithCount(@Param("status") String status);

    // 检查课程编号是否已存在
    @Select("SELECT COUNT(*) FROM course WHERE course_no = #{courseNo} AND id != #{id}")
    int countByCourseNo(@Param("courseNo") String courseNo, @Param("id") Integer id);

    // 更新课程状态
    @Update("UPDATE course SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);
}
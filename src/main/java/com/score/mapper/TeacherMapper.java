package com.score.mapper;

import com.score.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("SELECT * FROM teacher ORDER BY id DESC")
    List<Teacher> findAll();

    @Select("SELECT * FROM teacher WHERE id = #{id}")
    Teacher findById(Integer id);

    @Select("SELECT * FROM teacher WHERE teacher_no = #{teacherNo}")
    Teacher findByTeacherNo(@Param("teacherNo") String teacherNo);

    @Insert("INSERT INTO teacher(teacher_no, name, gender, phone, email, title, department) " +
            "VALUES(#{teacherNo}, #{name}, #{gender}, #{phone}, #{email}, #{title}, #{department})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Teacher teacher);

    @Update("UPDATE teacher SET teacher_no=#{teacherNo}, name=#{name}, gender=#{gender}, " +
            "phone=#{phone}, email=#{email}, title=#{title}, department=#{department} WHERE id=#{id}")
    void update(Teacher teacher);

    @Delete("DELETE FROM teacher WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM teacher WHERE name LIKE CONCAT('%', #{keyword}, '%') OR teacher_no LIKE CONCAT('%', #{keyword}, '%')")
    List<Teacher> search(@Param("keyword") String keyword);

    @Select("SELECT COUNT(*) FROM teacher WHERE teacher_no = #{teacherNo} AND id != #{excludeId}")
    int countByTeacherNo(@Param("teacherNo") String teacherNo, @Param("excludeId") Integer excludeId);

    @Select("SELECT id, name FROM teacher ORDER BY name")
    List<Teacher> findAllSimple();
}
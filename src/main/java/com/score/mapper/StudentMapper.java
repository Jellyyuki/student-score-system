package com.score.mapper;

import com.score.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<Student> findAll();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(Integer id);

    @Insert("INSERT INTO student(id, student_no, name, gender, birth_date, class_name) " +
            "VALUES(#{id}, #{studentNo}, #{name}, #{gender}, #{birthDate}, #{className})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    @Update("UPDATE student SET student_no=#{studentNo}, name=#{name}, gender=#{gender}, " +
            "birth_date=#{birthDate}, class_name=#{className} WHERE id=#{id}")
    void update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteById(Integer id);
}

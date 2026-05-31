package com.score.mapper;

import com.score.entity.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassesMapper {

    @Select("SELECT c.*, t.name as headteacherName, " +
            "(SELECT COUNT(*) FROM student WHERE class_id = c.id) as studentCount " +
            "FROM classes c " +
            "LEFT JOIN teacher t ON c.headteacher_id = t.id " +
            "ORDER BY c.id DESC")
    List<Classes> findAll();

    @Select("SELECT c.*, t.name as headteacherName, " +
            "(SELECT COUNT(*) FROM student WHERE class_id = c.id) as studentCount " +
            "FROM classes c " +
            "LEFT JOIN teacher t ON c.headteacher_id = t.id " +
            "WHERE c.id = #{id}")
    Classes findById(Integer id);

    @Insert("INSERT INTO classes(class_no, class_name, grade, major, headteacher_id) " +
            "VALUES(#{classNo}, #{className}, #{grade}, #{major}, #{headteacherId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Classes classes);

    @Update("UPDATE classes SET class_no=#{classNo}, class_name=#{className}, " +
            "grade=#{grade}, major=#{major}, headteacher_id=#{headteacherId} WHERE id=#{id}")
    void update(Classes classes);

    @Delete("DELETE FROM classes WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT c.*, t.name as headteacherName " +
            "FROM classes c LEFT JOIN teacher t ON c.headteacher_id = t.id " +
            "WHERE c.class_name LIKE CONCAT('%', #{keyword}, '%') OR c.class_no LIKE CONCAT('%', #{keyword}, '%') " +
            "ORDER BY c.id DESC")
    List<Classes> search(@Param("keyword") String keyword);

    @Select("SELECT COUNT(*) FROM classes WHERE class_no = #{classNo} AND id != #{excludeId}")
    int countByClassNo(@Param("classNo") String classNo, @Param("excludeId") Integer excludeId);

    @Select("SELECT id, class_name FROM classes ORDER BY class_name")
    List<Classes> findAllSimple();
}
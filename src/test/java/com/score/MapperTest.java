package com.score;

import com.score.entity.Student;
import com.score.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testFindAllStudents() {
        List<Student> students = studentMapper.findAll();
        System.out.println("========== 学生列表 ==========");
        students.forEach(System.out::println);
        // 断言至少有一条数据（根据你之前插入的测试数据）
        assert students.size() > 0;
    }

    @Test
    public void testFindStudentById() {
        Student student = studentMapper.findById(1);
        System.out.println("查到学生：" + student);
        assert student != null;
    }
}
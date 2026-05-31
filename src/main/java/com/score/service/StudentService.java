package com.score.service;

import com.score.entity.Student;
import com.score.entity.StudentScoreVO;

import java.util.List;

public interface StudentService {

    // 基础查询
    List<Student> findAll();

    List<Student> search(String keyword);

    Student findById(Integer id);

    // 班级筛选
    List<Student> findByClass(String className);

    List<Student> searchByClass(String keyword, String className);

    List<String> findAllClasses();

    // 增删改
    void save(Student student);

    void update(Student student);

    void deleteById(Integer id);

    // 学号校验
    boolean isStudentNoExists(String studentNo, Integer excludeId);

    // 成绩汇总
    StudentScoreVO getStudentScoreSummary(Integer studentId);
}
package com.score.service;

import com.score.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Integer id);
    void save(Student student);
    void update(Student student);
    void deleteById(Integer id);
    List<Student> search(String keyword);
}
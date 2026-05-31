package com.score.service;

import com.score.entity.Teacher;
import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();
    List<Teacher> search(String keyword);
    Teacher findById(Integer id);
    void save(Teacher teacher);
    void update(Teacher teacher);
    void deleteById(Integer id);
    boolean isTeacherNoExists(String teacherNo, Integer excludeId);
    List<Teacher> findAllSimple();
}
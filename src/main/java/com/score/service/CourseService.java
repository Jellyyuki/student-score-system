package com.score.service;

import com.score.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Integer id);
    void save(Course course);
    void update(Course course);
    void deleteById(Integer id);
    List<Course> search(String keyword);  // 添加搜索方法
}
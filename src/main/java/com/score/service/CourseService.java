package com.score.service;

import com.score.entity.Course;
import java.util.List;

public interface CourseService {

    List<Course> findAll();

    List<Course> findAllWithCount();

    List<Course> searchWithCount(String keyword);

    List<Course> findByStatusWithCount(String status);

    Course findById(Integer id);

    void save(Course course);

    void update(Course course);

    void deleteById(Integer id);

    boolean isCourseNoExists(String courseNo, Integer excludeId);

    void updateStatus(Integer id, String status);
}
package com.score.service.impl;

import com.score.entity.Course;
import com.score.mapper.CourseMapper;
import com.score.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public List<Course> findAllWithCount() {
        return courseMapper.findAllWithCount();
    }

    @Override
    public List<Course> searchWithCount(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return courseMapper.findAllWithCount();
        }
        return courseMapper.searchWithCount(keyword);
    }

    @Override
    public List<Course> findByStatusWithCount(String status) {
        if (status == null || status.isEmpty() || "全部".equals(status)) {
            return courseMapper.findAllWithCount();
        }
        return courseMapper.findByStatusWithCount(status);
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.findById(id);
    }

    @Override
    @Transactional
    public void save(Course course) {
        // 检查课程编号是否重复
        if (isCourseNoExists(course.getCourseNo(), null)) {
            throw new RuntimeException("课程编号已存在！");
        }
        if (course.getStatus() == null || course.getStatus().isEmpty()) {
            course.setStatus("进行中");
        }
        courseMapper.insert(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        // 检查课程编号是否重复（排除自身）
        if (isCourseNoExists(course.getCourseNo(), course.getId())) {
            throw new RuntimeException("课程编号已存在！");
        }
        courseMapper.update(course);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public boolean isCourseNoExists(String courseNo, Integer excludeId) {
        int count = courseMapper.countByCourseNo(courseNo, excludeId != null ? excludeId : 0);
        return count > 0;
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, String status) {
        courseMapper.updateStatus(id, status);
    }
}
package com.score.service.impl;

import com.score.entity.Teacher;
import com.score.mapper.TeacherMapper;
import com.score.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    @Override
    public List<Teacher> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return teacherMapper.findAll();
        }
        return teacherMapper.search(keyword);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherMapper.findById(id);
    }

    @Override
    public void save(Teacher teacher) {
        if (isTeacherNoExists(teacher.getTeacherNo(), null)) {
            throw new RuntimeException("工号已存在！");
        }
        teacherMapper.insert(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        if (isTeacherNoExists(teacher.getTeacherNo(), teacher.getId())) {
            throw new RuntimeException("工号已存在！");
        }
        teacherMapper.update(teacher);
    }

    @Override
    public void deleteById(Integer id) {
        teacherMapper.deleteById(id);
    }

    @Override
    public boolean isTeacherNoExists(String teacherNo, Integer excludeId) {
        int count = teacherMapper.countByTeacherNo(teacherNo, excludeId != null ? excludeId : 0);
        return count > 0;
    }

    @Override
    public List<Teacher> findAllSimple() {
        return teacherMapper.findAllSimple();
    }
}
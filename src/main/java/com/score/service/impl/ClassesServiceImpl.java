package com.score.service.impl;

import com.score.entity.Classes;
import com.score.mapper.ClassesMapper;
import com.score.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public List<Classes> findAll() {
        return classesMapper.findAll();
    }

    @Override
    public List<Classes> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return classesMapper.findAll();
        }
        return classesMapper.search(keyword);
    }

    @Override
    public Classes findById(Integer id) {
        return classesMapper.findById(id);
    }

    @Override
    public void save(Classes classes) {
        if (isClassNoExists(classes.getClassNo(), null)) {
            throw new RuntimeException("班级编号已存在！");
        }
        classesMapper.insert(classes);
    }

    @Override
    public void update(Classes classes) {
        if (isClassNoExists(classes.getClassNo(), classes.getId())) {
            throw new RuntimeException("班级编号已存在！");
        }
        classesMapper.update(classes);
    }

    @Override
    public void deleteById(Integer id) {
        classesMapper.deleteById(id);
    }

    @Override
    public boolean isClassNoExists(String classNo, Integer excludeId) {
        int count = classesMapper.countByClassNo(classNo, excludeId != null ? excludeId : 0);
        return count > 0;
    }

    @Override
    public List<Classes> findAllSimple() {
        return classesMapper.findAllSimple();
    }
}
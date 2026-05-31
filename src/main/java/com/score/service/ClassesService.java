package com.score.service;

import com.score.entity.Classes;
import java.util.List;

public interface ClassesService {
    List<Classes> findAll();
    List<Classes> search(String keyword);
    Classes findById(Integer id);
    void save(Classes classes);
    void update(Classes classes);
    void deleteById(Integer id);
    boolean isClassNoExists(String classNo, Integer excludeId);
    List<Classes> findAllSimple();
}
package com.score.service;

import com.score.entity.Score;
import java.util.List;

public interface ScoreService {
    List<Score> findAllWithInfo();           // 获取所有成绩（带学生课程信息）
    List<Score> search(String keyword);      // 模糊搜索成绩
    Score findById(Integer id);
    void save(Score score) throws Exception;  // 添加，可能抛出重复异常
    void update(Score score);
    void deleteById(Integer id);
    boolean isExist(Integer studentId, Integer courseId); // 检查是否已存在
}
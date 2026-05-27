package com.score.service.impl;

import com.score.entity.Score;
import com.score.mapper.ScoreMapper;
import com.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> findAllWithInfo() {
        return scoreMapper.findAllWithInfo();
    }

    @Override
    public List<Score> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return scoreMapper.findAllWithInfo();
        }
        return scoreMapper.searchWithInfo(keyword);
    }

    @Override
    public Score findById(Integer id) {
        return scoreMapper.findById(id);
    }

    @Override
    public void save(Score score) throws Exception {
        // 检查重复：同一学生同一课程只能有一条成绩
        if (isExist(score.getStudentId(), score.getCourseId())) {
            throw new Exception("该学生此课程的成绩已存在，请勿重复添加！");
        }
        scoreMapper.insert(score);
    }

    @Override
    public void update(Score score) {
        // 更新时不需要检查重复（允许修改分数，但保持学生和课程不变）
        scoreMapper.update(score);
    }

    @Override
    public void deleteById(Integer id) {
        scoreMapper.deleteById(id);
    }

    @Override
    public boolean isExist(Integer studentId, Integer courseId) {
        return scoreMapper.countByStudentAndCourse(studentId, courseId) > 0;
    }
}
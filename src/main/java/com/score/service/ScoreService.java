package com.score.service;

import com.score.entity.Score;
import com.score.entity.ScoreStatisticsVO;

import java.util.List;

public interface ScoreService {

    List<Score> findAll();

    List<Score> findAllWithInfo();

    List<Score> searchWithInfo(String keyword);

    Score findById(Integer id);

    void save(Score score);

    void update(Score score);

    void deleteById(Integer id);

    ScoreStatisticsVO getStatistics();
}
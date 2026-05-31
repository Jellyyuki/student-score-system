package com.score.service.impl;

import com.score.entity.Score;
import com.score.entity.ScoreStatisticsVO;
import com.score.mapper.ScoreMapper;
import com.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public List<Score> findAllWithInfo() {
        return scoreMapper.findAllWithInfo();
    }

    @Override
    public List<Score> searchWithInfo(String keyword) {
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
    @Transactional
    public void save(Score score) {
        int count = scoreMapper.countByStudentAndCourse(score.getStudentId(), score.getCourseId());
        if (count > 0) {
            throw new RuntimeException("该学生此课程的成绩已存在，请使用编辑功能！");
        }
        scoreMapper.insert(score);
    }

    @Override
    @Transactional
    public void update(Score score) {
        scoreMapper.update(score);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        scoreMapper.deleteById(id);
    }

    @Override
    public ScoreStatisticsVO getStatistics() {
        ScoreStatisticsVO vo = new ScoreStatisticsVO();

        // 1. 基础统计
        Map<String, Object> basicStats = scoreMapper.getBasicStatistics();
        if (basicStats != null) {
            // 平均分
            Object avgScoreObj = basicStats.get("averageScore");
            if (avgScoreObj instanceof BigDecimal) {
                vo.setAverageScore(((BigDecimal) avgScoreObj).doubleValue());
            } else if (avgScoreObj instanceof Double) {
                vo.setAverageScore((Double) avgScoreObj);
            } else if (avgScoreObj != null) {
                vo.setAverageScore(Double.parseDouble(avgScoreObj.toString()));
            } else {
                vo.setAverageScore(0.0);
            }

            // 最高分
            Object maxScoreObj = basicStats.get("maxScore");
            if (maxScoreObj instanceof Number) {
                vo.setMaxScore(((Number) maxScoreObj).intValue());
            } else if (maxScoreObj != null) {
                vo.setMaxScore(Integer.parseInt(maxScoreObj.toString()));
            } else {
                vo.setMaxScore(0);
            }

            // 最低分
            Object minScoreObj = basicStats.get("minScore");
            if (minScoreObj instanceof Number) {
                vo.setMinScore(((Number) minScoreObj).intValue());
            } else if (minScoreObj != null) {
                vo.setMinScore(Integer.parseInt(minScoreObj.toString()));
            } else {
                vo.setMinScore(0);
            }

            // 总记录数
            Object totalCountObj = basicStats.get("totalCount");
            int totalCount = 0;
            if (totalCountObj instanceof Number) {
                totalCount = ((Number) totalCountObj).intValue();
            } else if (totalCountObj != null) {
                totalCount = Integer.parseInt(totalCountObj.toString());
            }
            vo.setTotalCount(totalCount);

            // 及格人数
            Object passCountObj = basicStats.get("passCount");
            int passCount = 0;
            if (passCountObj instanceof Number) {
                passCount = ((Number) passCountObj).intValue();
            } else if (passCountObj != null) {
                passCount = Integer.parseInt(passCountObj.toString());
            }
            vo.setPassCount(passCount);

            // 计算及格率
            if (totalCount > 0) {
                double passRate = (passCount * 100.0) / totalCount;
                vo.setPassRate(Math.round(passRate * 10) / 10.0);
            } else {
                vo.setPassRate(0.0);
            }
        }

        // 2. 各课程平均分 - 处理 BigDecimal 转换
        List<Map<String, Object>> courseAvgList = scoreMapper.getCourseAvgScores();
        if (courseAvgList != null) {
            for (Map<String, Object> item : courseAvgList) {
                Object avgScore = item.get("avgScore");
                if (avgScore instanceof BigDecimal) {
                    item.put("avgScore", ((BigDecimal) avgScore).doubleValue());
                }
            }
        }
        vo.setCourseAvgScores(courseAvgList);

        // 3. 分数段分布统计
        Map<String, Object> distribution = scoreMapper.getScoreDistribution();
        if (distribution != null) {
            // 优秀 (90-100)
            Object excellentObj = distribution.get("excellentCount");
            vo.setExcellentCount(excellentObj instanceof Number ? ((Number) excellentObj).intValue() : 0);

            // 良好 (80-89)
            Object goodObj = distribution.get("goodCount");
            vo.setGoodCount(goodObj instanceof Number ? ((Number) goodObj).intValue() : 0);

            // 中等 (70-79)
            Object mediumObj = distribution.get("mediumCount");
            vo.setMediumCount(mediumObj instanceof Number ? ((Number) mediumObj).intValue() : 0);

            // 及格 (60-69)
            Object passObj = distribution.get("passCount");
            vo.setPassCount2(passObj instanceof Number ? ((Number) passObj).intValue() : 0);

            // 不及格 (0-59)
            Object failObj = distribution.get("failCount");
            vo.setFailCount(failObj instanceof Number ? ((Number) failObj).intValue() : 0);
        }

        return vo;
    }
}
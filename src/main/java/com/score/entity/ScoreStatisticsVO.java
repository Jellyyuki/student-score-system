package com.score.entity;

import java.util.List;
import java.util.Map;

public class ScoreStatisticsVO {
    // 基础统计
    private Double averageScore;      // 平均分
    private Integer maxScore;         // 最高分
    private Integer minScore;         // 最低分
    private Double passRate;          // 及格率（%）
    private Integer totalCount;       // 总成绩记录数
    private Integer passCount;        // 及格人数（>=60）

    // 课程平均分（用于柱状图）
    private List<Map<String, Object>> courseAvgScores;

    // 分数段分布
    private Integer excellentCount;   // 优秀 (90-100)
    private Integer goodCount;        // 良好 (80-89)
    private Integer mediumCount;      // 中等 (70-79)
    private Integer passCount2;       // 及格 (60-69)
    private Integer failCount;        // 不及格 (0-59)

    // getter 和 setter
    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public List<Map<String, Object>> getCourseAvgScores() {
        return courseAvgScores;
    }

    public void setCourseAvgScores(List<Map<String, Object>> courseAvgScores) {
        this.courseAvgScores = courseAvgScores;
    }

    public Integer getExcellentCount() {
        return excellentCount;
    }

    public void setExcellentCount(Integer excellentCount) {
        this.excellentCount = excellentCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getMediumCount() {
        return mediumCount;
    }

    public void setMediumCount(Integer mediumCount) {
        this.mediumCount = mediumCount;
    }

    public Integer getPassCount2() {
        return passCount2;
    }

    public void setPassCount2(Integer passCount2) {
        this.passCount2 = passCount2;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
}
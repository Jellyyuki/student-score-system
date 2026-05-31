package com.score.entity;

import java.util.List;

public class StudentScoreVO {
    private Integer studentId;
    private String studentNo;
    private String studentName;
    private String className;
    private Integer totalScore;      // 总分
    private Double avgScore;         // 平均分
    private Integer courseCount;     // 课程数量
    private List<CourseScore> courseScores;  // 各科成绩列表

    // 内部类：单科成绩
    public static class CourseScore {
        private Integer courseId;
        private String courseNo;
        private String courseName;
        private Integer credit;
        private Double score;

        // 构造方法
        public CourseScore() {}

        public CourseScore(Integer courseId, String courseNo, String courseName, Integer credit, Double score) {
            this.courseId = courseId;
            this.courseNo = courseNo;
            this.courseName = courseName;
            this.credit = credit;
            this.score = score;
        }

        // getter/setter
        public Integer getCourseId() { return courseId; }
        public void setCourseId(Integer courseId) { this.courseId = courseId; }
        public String getCourseNo() { return courseNo; }
        public void setCourseNo(String courseNo) { this.courseNo = courseNo; }
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        public Integer getCredit() { return credit; }
        public void setCredit(Integer credit) { this.credit = credit; }
        public Double getScore() { return score; }
        public void setScore(Double score) { this.score = score; }
    }

    // getter/setter
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    public Double getAvgScore() { return avgScore; }
    public void setAvgScore(Double avgScore) { this.avgScore = avgScore; }
    public Integer getCourseCount() { return courseCount; }
    public void setCourseCount(Integer courseCount) { this.courseCount = courseCount; }
    public List<CourseScore> getCourseScores() { return courseScores; }
    public void setCourseScores(List<CourseScore> courseScores) { this.courseScores = courseScores; }
}

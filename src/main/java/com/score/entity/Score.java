package com.score.entity;

public class Score {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Double score;

    // 以下字段用于联查展示
    private String studentName;
    private String studentNo;
    private String courseName;
    private String courseNo;

    // 构造方法
    public Score() {

    }

    // ========== getter 和 setter ==========

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    @Override
    public String toString() {
        return "Score{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", score=" + score + "}";
    }
}
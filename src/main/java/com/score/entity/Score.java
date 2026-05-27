package com.score.entity;

public class Score {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer score;

    //构造方法
    public Score(){

    }

    //getter/setter

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", score=" + score + "}";
    }
}

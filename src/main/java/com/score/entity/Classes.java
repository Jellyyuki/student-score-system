package com.score.entity;

import java.time.LocalDateTime;

public class Classes {
    private Integer id;
    private String classNo;
    private String className;
    private String grade;      // 年级
    private String major;      // 专业
    private Integer headteacherId;  // 班主任ID
    private Integer studentCount;   // 学生人数（非数据库字段，用于展示）
    private LocalDateTime createTime;

    // 关联字段（用于展示）
    private String headteacherName;  // 班主任姓名

    // 构造方法
    public Classes() {}

    // getter/setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getClassNo() { return classNo; }
    public void setClassNo(String classNo) { this.classNo = classNo; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public Integer getHeadteacherId() { return headteacherId; }
    public void setHeadteacherId(Integer headteacherId) { this.headteacherId = headteacherId; }

    public Integer getStudentCount() { return studentCount; }
    public void setStudentCount(Integer studentCount) { this.studentCount = studentCount; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public String getHeadteacherName() { return headteacherName; }
    public void setHeadteacherName(String headteacherName) { this.headteacherName = headteacherName; }
}
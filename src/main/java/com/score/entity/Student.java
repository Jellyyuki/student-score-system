package com.score.entity;
import java.time.LocalDate;

public class Student {
    private Integer id;
    private String studentNo;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String className;

    // 构造方法
    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", studentNo='" + studentNo + "', name='" + name + "', gender='" + gender + "', birthDate=" + birthDate + ", className='" + className + "'}";
    }
}

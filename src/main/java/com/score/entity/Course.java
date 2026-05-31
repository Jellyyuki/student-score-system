package com.score.entity;

public class Course {
        private Integer id;
        private String courseNo;
        private String courseName;
        private Integer credit;
        private String status;  // 新增：课程状态（进行中/已结束）
        private Integer studentCount;  // 新增：选课人数（非数据库字段，用于展示）

        // 构造方法
        public Course() {
        }

        // getter 和 setter
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getCourseNo() {
                return courseNo;
        }

        public void setCourseNo(String courseNo) {
                this.courseNo = courseNo;
        }

        public String getCourseName() {
                return courseName;
        }

        public void setCourseName(String courseName) {
                this.courseName = courseName;
        }

        public Integer getCredit() {
                return credit;
        }

        public void setCredit(Integer credit) {
                this.credit = credit;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public Integer getStudentCount() {
                return studentCount;
        }

        public void setStudentCount(Integer studentCount) {
                this.studentCount = studentCount;
        }

        @Override
        public String toString() {
                return "Course{id=" + id + ", courseNo=" + courseNo + ", courseName=" + courseName +
                        ", credit=" + credit + ", status=" + status + "}";
        }
}
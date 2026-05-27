package com.score.entity;

public class Course {
        private Integer id;
        private String courseNo;
        private String courseName;
        private Integer credit;
        //构建方法
        public Course() {
        }

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

        @Override
        public String toString() {
                return "Course{id=" + id + ", courseNo='" + courseNo + "', courseName='" + courseName + "', credit=" + credit + "}";
        }
}

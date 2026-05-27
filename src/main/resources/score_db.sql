CREATE DATABASE IF NOT EXISTS score_db;
USE score_db;

-- 学生表
CREATE TABLE `student` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `student_no` varchar(20) NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `gender` varchar(4) DEFAULT NULL,
                           `birth_date` date DEFAULT NULL,
                           `class_name` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uk_student_no` (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 课程表
CREATE TABLE `course` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `course_no` varchar(20) NOT NULL,
                          `course_name` varchar(100) NOT NULL,
                          `credit` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_course_no` (`course_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 成绩表
CREATE TABLE `score` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `student_id` int(11) NOT NULL,
                         `course_id` int(11) NOT NULL,
                         `score` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `fk_score_student` (`student_id`),
                         KEY `fk_score_course` (`course_id`),
                         CONSTRAINT `fk_score_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
                         CONSTRAINT `fk_score_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户表（管理员）
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试数据
INSERT INTO `user` VALUES (1,'admin','123456');
INSERT INTO `student` VALUES (1,'2021001','张三','男','2002-03-12','计科1班');
INSERT INTO `student` VALUES (2,'2021002','李四','女','2003-05-20','计科1班');
INSERT INTO `course` VALUES (1,'CS101','Java程序设计',4);
INSERT INTO `course` VALUES (2,'CS102','数据库原理',3);
INSERT INTO `score` VALUES (1,1,1,85);
INSERT INTO `score` VALUES (2,1,2,90);
INSERT INTO `score` VALUES (3,2,1,78);
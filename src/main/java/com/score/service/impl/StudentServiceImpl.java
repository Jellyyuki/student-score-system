package com.score.service.impl;

import com.score.entity.Student;
import com.score.entity.StudentScoreVO;
import com.score.mapper.StudentMapper;
import com.score.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<Student> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentMapper.findAll();
        }
        return studentMapper.search(keyword);
    }

    @Override
    public List<Student> findByClass(String className) {
        if (className == null || className.trim().isEmpty()) {
            return studentMapper.findAll();
        }
        return studentMapper.findByClass(className);
    }

    @Override
    public List<Student> searchByClass(String keyword, String className) {
        if ((className == null || className.trim().isEmpty()) &&
                (keyword == null || keyword.trim().isEmpty())) {
            return studentMapper.findAll();
        }
        if (className == null || className.trim().isEmpty()) {
            return studentMapper.search(keyword);
        }
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentMapper.findByClass(className);
        }
        return studentMapper.searchByClass(keyword, className);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void save(Student student) {
        // 检查学号是否重复
        if (isStudentNoExists(student.getStudentNo(), null)) {
            throw new RuntimeException("学号已存在！");
        }
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        // 检查学号是否重复（排除自身）
        if (isStudentNoExists(student.getStudentNo(), student.getId())) {
            throw new RuntimeException("学号已存在！");
        }
        studentMapper.update(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public List<String> findAllClasses() {
        return studentMapper.findAllClasses();
    }

    @Override
    public boolean isStudentNoExists(String studentNo, Integer excludeId) {
        int count = studentMapper.countByStudentNo(studentNo, excludeId != null ? excludeId : 0);
        return count > 0;
    }

    @Override
    public StudentScoreVO getStudentScoreSummary(Integer studentId) {
        StudentScoreVO vo = new StudentScoreVO();

        // 1. 获取学生基本信息
        Student student = studentMapper.findById(studentId);
        if (student == null) {
            return null;
        }

        vo.setStudentId(student.getId());
        vo.setStudentNo(student.getStudentNo());
        vo.setStudentName(student.getName());
        vo.setClassName(student.getClassName());

        // 2. 获取各科成绩
        List<StudentScoreVO.CourseScore> courseScores = studentMapper.getStudentCourseScores(studentId);
        vo.setCourseScores(courseScores);

        // 3. 获取成绩汇总（总分、平均分、课程数）
        Map<String, Object> summary = studentMapper.getScoreSummary(studentId);
        if (summary != null && summary.get("courseCount") != null) {
            Object courseCountObj = summary.get("courseCount");
            if (courseCountObj instanceof Long) {
                vo.setCourseCount(((Long) courseCountObj).intValue());
            } else if (courseCountObj instanceof Integer) {
                vo.setCourseCount((Integer) courseCountObj);
            } else {
                vo.setCourseCount(0);
            }

            Object totalScoreObj = summary.get("totalScore");
            if (totalScoreObj instanceof java.math.BigDecimal) {
                vo.setTotalScore(((java.math.BigDecimal) totalScoreObj).intValue());
            } else if (totalScoreObj instanceof Long) {
                vo.setTotalScore(((Long) totalScoreObj).intValue());
            } else if (totalScoreObj instanceof Integer) {
                vo.setTotalScore((Integer) totalScoreObj);
            } else {
                vo.setTotalScore(0);
            }

            Object avgScoreObj = summary.get("avgScore");
            if (avgScoreObj instanceof java.math.BigDecimal) {
                vo.setAvgScore(((java.math.BigDecimal) avgScoreObj).doubleValue());
            } else if (avgScoreObj instanceof Double) {
                vo.setAvgScore((Double) avgScoreObj);
            } else if (avgScoreObj != null) {
                vo.setAvgScore(Double.parseDouble(avgScoreObj.toString()));
            } else {
                vo.setAvgScore(0.0);
            }
        } else {
            vo.setCourseCount(0);
            vo.setTotalScore(0);
            vo.setAvgScore(0.0);
        }

        return vo;
    }
}
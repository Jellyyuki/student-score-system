package com.score.controller;

import com.score.entity.Student;
import com.score.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 学生列表（支持搜索）
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = studentService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            students = studentService.findAll();
        }
        model.addAttribute("students", students);
        return "studentList";
    }

    // 删除学生
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }

    // 显示添加表单
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    // 显示编辑表单
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "studentForm";
    }

    // 保存（添加或更新）
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        if (student.getId() == null) {
            // 添加
            studentService.save(student);
        } else {
            // 更新
            studentService.update(student);
        }
        return "redirect:/students/list";
    }
}
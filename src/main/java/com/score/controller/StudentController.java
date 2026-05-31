package com.score.controller;

import com.score.entity.Student;
import com.score.entity.StudentScoreVO;
import com.score.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 学生列表（支持搜索和班级筛选）
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String className,
                       Model model) {
        List<Student> students;

        // 根据关键词和班级筛选
        if ((className != null && !className.trim().isEmpty()) ||
                (keyword != null && !keyword.trim().isEmpty())) {
            students = studentService.searchByClass(keyword, className);
        } else {
            students = studentService.findAll();
        }

        // 获取班级列表用于下拉框
        List<String> classes = studentService.findAllClasses();

        // ✅ 正确写法
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        model.addAttribute("className", className);
        model.addAttribute("classList", classes);

        return "studentList";
    }

    // 删除学生
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "学生删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
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
    public String save(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            if (student.getId() == null) {
                studentService.save(student);
                redirectAttributes.addFlashAttribute("success", "学生添加成功！");
            } else {
                studentService.update(student);
                redirectAttributes.addFlashAttribute("success", "学生更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            if (student.getId() == null) {
                return "redirect:/students/add";
            } else {
                return "redirect:/students/edit/" + student.getId();
            }
        }
        return "redirect:/students/list";
    }

    // 获取学生成绩汇总（AJAX接口）
    @GetMapping("/scoreSummary/{id}")
    @ResponseBody
    public StudentScoreVO getScoreSummary(@PathVariable Integer id) {
        return studentService.getStudentScoreSummary(id);
    }
}
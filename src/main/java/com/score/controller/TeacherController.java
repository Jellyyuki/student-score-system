package com.score.controller;

import com.score.entity.Teacher;
import com.score.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Teacher> teachers;
        if (keyword != null && !keyword.trim().isEmpty()) {
            teachers = teacherService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            teachers = teacherService.findAll();
        }
        model.addAttribute("teachers", teachers);
        return "teacherList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacherForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            if (teacher.getId() == null) {
                teacherService.save(teacher);
                redirectAttributes.addFlashAttribute("success", "教师添加成功！");
            } else {
                teacherService.update(teacher);
                redirectAttributes.addFlashAttribute("success", "教师更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/teachers/add";
        }
        return "redirect:/teachers/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            teacherService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "教师删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        return "redirect:/teachers/list";
    }
}
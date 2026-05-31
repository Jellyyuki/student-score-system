package com.score.controller;

import com.score.entity.Classes;
import com.score.entity.Teacher;
import com.score.service.ClassesService;
import com.score.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Classes> classes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            classes = classesService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            classes = classesService.findAll();
        }
        model.addAttribute("classes", classes);
        return "classList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("classes", new Classes());
        model.addAttribute("teachers", teacherService.findAllSimple());
        return "classForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Classes classes = classesService.findById(id);
        model.addAttribute("classes", classes);
        model.addAttribute("teachers", teacherService.findAllSimple());
        return "classForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Classes classes, RedirectAttributes redirectAttributes) {
        try {
            if (classes.getId() == null) {
                classesService.save(classes);
                redirectAttributes.addFlashAttribute("success", "班级添加成功！");
            } else {
                classesService.update(classes);
                redirectAttributes.addFlashAttribute("success", "班级更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/classes/add";
        }
        return "redirect:/classes/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            classesService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "班级删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        return "redirect:/classes/list";
    }
}
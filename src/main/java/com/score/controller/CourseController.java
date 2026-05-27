package com.score.controller;

import com.score.entity.Course;
import com.score.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("courses", courseService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "courseList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("course", new Course());
        return "courseForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        return "courseForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Course course) {
        if (course.getId() == null) {
            courseService.save(course);
        } else {
            courseService.update(course);
        }
        return "redirect:/courses/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        courseService.deleteById(id);
        return "redirect:/courses/list";
    }
}
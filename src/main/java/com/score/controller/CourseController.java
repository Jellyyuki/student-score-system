package com.score.controller;

import com.score.entity.Course;
import com.score.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String status,
                       Model model) {
        List<Course> courses;

        // 根据是否有状态筛选和关键词选择查询方式
        if (status != null && !status.isEmpty() && !"全部".equals(status)) {
            courses = courseService.findByStatusWithCount(status);
            model.addAttribute("currentStatus", status);
        } else if (keyword != null && !keyword.trim().isEmpty()) {
            courses = courseService.searchWithCount(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            courses = courseService.findAllWithCount();
        }

        model.addAttribute("courses", courses);
        model.addAttribute("statusList", new String[]{"全部", "进行中", "已结束"});
        return "courseList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("course", new Course());
        return "courseForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        return "courseForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        try {
            if (course.getId() == null) {
                courseService.save(course);
                redirectAttributes.addFlashAttribute("success", "课程添加成功！");
            } else {
                courseService.update(course);
                redirectAttributes.addFlashAttribute("success", "课程更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            if (course.getId() == null) {
                return "redirect:/courses/add";
            } else {
                return "redirect:/courses/edit/" + course.getId();
            }
        }
        return "redirect:/courses/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "课程删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        return "redirect:/courses/list";
    }

    // 更新课程状态（AJAX 或表单提交）
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer id,
                               @RequestParam String status,
                               RedirectAttributes redirectAttributes) {
        try {
            courseService.updateStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "课程状态更新成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "状态更新失败：" + e.getMessage());
        }
        return "redirect:/courses/list";
    }
}
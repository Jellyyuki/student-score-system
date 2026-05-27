package com.score.controller;

import com.score.entity.Score;
import com.score.entity.Student;
import com.score.entity.Course;
import com.score.service.ScoreService;
import com.score.service.StudentService;
import com.score.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Score> scores = scoreService.search(keyword);
        model.addAttribute("scores", scores);
        model.addAttribute("keyword", keyword);
        return "scoreList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("score", new Score());
        // 获取所有学生和课程，用于下拉框选择
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "scoreForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Score score = scoreService.findById(id);
        model.addAttribute("score", score);
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "scoreForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Score score, RedirectAttributes redirectAttributes) {
        try {
            if (score.getId() == null) {
                scoreService.save(score);
                redirectAttributes.addFlashAttribute("success", "成绩添加成功！");
            } else {
                scoreService.update(score);
                redirectAttributes.addFlashAttribute("success", "成绩更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/scores/add";
        }
        return "redirect:/scores/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        scoreService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "成绩删除成功！");
        return "redirect:/scores/list";
    }
}
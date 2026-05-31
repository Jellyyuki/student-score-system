package com.score.controller;

import com.score.entity.Score;
import com.score.entity.ScoreStatisticsVO;
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

    /**
     * 成绩列表页
     * URL: /scores/list
     */
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Score> scores;
        if (keyword != null && !keyword.trim().isEmpty()) {
            scores = scoreService.searchWithInfo(keyword);
        } else {
            scores = scoreService.findAllWithInfo();
        }

        // 获取统计数据（仅在非搜索状态下显示全部统计）
        if (keyword == null || keyword.trim().isEmpty()) {
            ScoreStatisticsVO statistics = scoreService.getStatistics();
            model.addAttribute("statistics", statistics);
        }

        model.addAttribute("scores", scores);
        model.addAttribute("keyword", keyword);
        return "scoreList";
    }

    /**
     * 跳转到录入成绩页面
     * URL: /scores/add
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("score", new Score());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "scoreForm";
    }

    /**
     * 跳转到编辑成绩页面
     * URL: /scores/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Score score = scoreService.findById(id);
        model.addAttribute("score", score);
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "scoreForm";
    }

    /**
     * 保存成绩（新增或修改）
     * URL: /scores/save
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Score score, RedirectAttributes redirectAttributes) {
        try {
            if (score.getId() == null) {
                // 新增成绩
                scoreService.save(score);
                redirectAttributes.addFlashAttribute("success", "成绩添加成功！");
            } else {
                // 修改成绩
                scoreService.update(score);
                redirectAttributes.addFlashAttribute("success", "成绩更新成功！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            if (score.getId() == null) {
                return "redirect:/scores/add";
            } else {
                return "redirect:/scores/edit/" + score.getId();
            }
        }
        return "redirect:/scores/list";
    }

    /**
     * 删除成绩
     * URL: /scores/delete/{id}
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            scoreService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "成绩删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        return "redirect:/scores/list";
    }
}
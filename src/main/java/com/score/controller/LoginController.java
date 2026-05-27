package com.score.controller;

import com.score.entity.User;
import com.score.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 显示登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            // 登录成功，将用户信息存入 Session
            session.setAttribute("loginUser", user);
            // 重定向到学生列表页
            return "redirect:/students/list";
        } else {
            // 登录失败，返回登录页并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // 清除 Session
        return "redirect:/login";
    }
}
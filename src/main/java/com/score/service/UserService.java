package com.score.service;

import com.score.entity.User;

public interface UserService {
    // 登录验证：成功返回 User 对象，失败返回 null
    User login(String username, String password);
}
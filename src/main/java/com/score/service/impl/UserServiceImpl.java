package com.score.service.impl;

import com.score.entity.User;
import com.score.mapper.UserMapper;
import com.score.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;   // 密码匹配，登录成功
        }
        return null;       // 用户名不存在或密码错误
    }
}
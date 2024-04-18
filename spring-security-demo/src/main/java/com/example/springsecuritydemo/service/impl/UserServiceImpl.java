package com.example.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecuritydemo.config.DBUserDetailsManager;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.mapper.UserMapper;
import com.example.springsecuritydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;
    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}

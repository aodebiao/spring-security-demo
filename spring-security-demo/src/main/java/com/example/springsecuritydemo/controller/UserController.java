package com.example.springsecuritydemo.controller;


import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/list")
    // 拥有ADMIN角色且用户名为admin才能访问此接口
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping("/add")
//    @PreAuthorize("hasRole('superAdmin')")
    // 拥有USER_ADD权限字符串才能访问
    @PreAuthorize("hasAuthority('USER_ADD')")
    public void save(@RequestBody User user){
        userService.saveUserDetails(user);
    }

}

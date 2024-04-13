package com.example.springsecuritydemo.controller;


import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

}

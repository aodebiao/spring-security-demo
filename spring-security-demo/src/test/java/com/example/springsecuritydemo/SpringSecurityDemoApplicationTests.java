package com.example.springsecuritydemo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityDemoApplicationTests {


    @Resource
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(4);
        System.out.println(encoder.encode("password"));
        System.out.println(passwordEncoder.encode("password"));
        System.out.println();
    }

}

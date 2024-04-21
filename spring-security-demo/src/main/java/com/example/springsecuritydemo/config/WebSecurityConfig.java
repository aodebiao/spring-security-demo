package com.example.springsecuritydemo.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 默念行为 ，不加也生效
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/add").hasAuthority("USER_ADD")
//                        .requestMatchers("/user/list").hasAuthority("USER_LIST")
                                .requestMatchers("/user/**").hasRole("ADMIN")
//                                .requestMatchers("/user/add").hasRole("")
                        .anyRequest().authenticated()

                );


//            .formLogin(Customizer.withDefaults()); // 注释此配置，会弹出默认的登录页面
           http.formLogin(form -> {
                form.loginPage("/login").permitAll()
                        .usernameParameter("myusername") // 修改html 登录表单中的用户名和密码参数key
                        .passwordParameter("mypassword")
                        .failureUrl("/login?error111") // 用户名和密码输入错误时，浏览器的url，前端 通过 <div th:if="${param.error111}"> 错误的用户名和密码</div>,展示错误信息
                        .successHandler(new MyAuthenticationSuccessHandler())
                        .failureHandler(new MyAuthenticationFailureHandler())

                ; // 自定义登录页面，需要手动施行，不然会无限重写向
            }); // 自定义登录页面
        http.logout(logout -> {
            // 注销成功的处理，返回json
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
        });


        http.exceptionHandling(e -> {
            // 处理未认证的请求
            e.authenticationEntryPoint(new MyAuthenticationEntryPoint());
            e.accessDeniedHandler(new MyAccessDeniedHandler());
        });

        http.sessionManagement(session -> {
            session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });


        http.cors(Customizer.withDefaults());

            // .httpBasic(Customizer.withDefaults());
        return http.build();
    }



    /**
     * 基于内存的用户认证
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails u = User.withUsername("user").password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG").roles("USER").build();
//        manager.createUser(User.withUserDetails(u).build());
//        return manager;
//    }





    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public static void main(String[] args) {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(delegatingPasswordEncoder.encode("111"));
        System.out.println(delegatingPasswordEncoder.encode("password"));
        System.out.println(delegatingPasswordEncoder.encode("password"));
    }

}

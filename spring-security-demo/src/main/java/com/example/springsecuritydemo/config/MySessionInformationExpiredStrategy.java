package com.example.springsecuritydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 会话过期处理
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {


        Map result = new HashMap<>();
        result.put("code", -1);
        result.put("message","该账号已从其他设备登录");

        HttpServletResponse resp = event.getResponse();;
        String json = JSON.toJSONString(result);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(json);
    }
}

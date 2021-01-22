package com.yuyu.auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuyu.common.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    SessionRegistry sessionRegistry;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String verifyCode = (String) request.getSession().getAttribute("verify_code");
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                String code = loginData.get("code");
                checkCode(code, verifyCode);
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            User principal = new User();
            principal.setUsername(username);
            sessionRegistry.registerNewSession(request.getSession(true).getId(), principal);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            checkCode(request.getParameter("code"), verifyCode);
            return super.attemptAuthentication(request, response);
        }
    }

    public void checkCode(String code, String verify_code) {
        if (StringUtils.isEmpty(code) || verify_code == null || !verify_code.equalsIgnoreCase(code)) {
            throw new AuthenticationServiceException("验证码错误"+code);
        }
    }

}

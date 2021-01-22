package com.yuyu.auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuyu.common.po.User;
import com.yuyu.auth.vo.UserVo;
import com.yuyu.common.utils.R;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService UserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html","/swagger-resources/**","/v2/api-docs").permitAll()
                .antMatchers("/vercode").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter out = response.getWriter();
                    R error = R.error(HttpStatus.SC_UNAUTHORIZED,"访问失败");
                    if (authException instanceof InsufficientAuthenticationException) {
                        error.put("data","请求失败，请登录或联系管理员");
                    }
                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(R.ok("注销成功")));
                    out.flush();
                    out.close();
                })
                .clearAuthentication(true).permitAll()
                .and()
                .csrf().disable()
                .addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
                    HttpServletResponse resp = event.getResponse();
                    resp.setContentType("application/json;charset=utf-8");
                    resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(R.error("您已在另一台设备登录，本次登录已下线!")));
                    out.flush();
                    out.close();
                }), ConcurrentSessionFilter.class)
                .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    FindByIndexNameSessionRepository sessionRepository;

    @Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            User user = (User) authentication.getPrincipal();
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVo.setAuthorities(user.getAuthorities());
            out.write(new ObjectMapper().writeValueAsString(R.ok("登录成功").put("data",userVo)));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            R error = R.error(HttpStatus.SC_UNAUTHORIZED,"登录失败");
            if (exception instanceof LockedException) {
                error.put("data","账户被锁定，请联系管理员");
            } else if (exception instanceof CredentialsExpiredException) {
                error.put("data","密码过期，请联系管理员");
            } else if (exception instanceof AccountExpiredException) {
                error.put("data","账户过期，请联系管理员");
            } else if (exception instanceof DisabledException) {
                error.put("data","账户被禁用，请联系管理员");
            } else if (exception instanceof BadCredentialsException) {
                error.put("data","用户名或者密码输入错误，请重新输入");
            } else {
                error.put("data",exception.getMessage());
            }
            out.write(new ObjectMapper().writeValueAsString(error));
            out.flush();
            out.close();
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/login");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }
}

//package com.example.ren.configuration;
//
//import com.example.ren.module.handler.MyAuthenctiationFailureHandler;
//import com.example.ren.module.handler.MyAuthenctiationSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Security配置类
// *
// * @author qiang.ren
// * @date 2018/4/23
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/user/login/view")
//                //登录需要经过的url请求
//                //.loginProcessingUrl("/authentication/form")
//                .successHandler(mySuccessHandler)
//                .failureHandler(myFailHandler)
//                .and()
//                //请求授权
//                .authorizeRequests()
//                //不需要权限认证的url
//                .antMatchers("/user/login/view").permitAll()
//                //任何请求
//                .anyRequest()
//                //需要身份认证
//                .authenticated()
//                .and()
//                //关闭跨站请求防护
//                .csrf().disable();
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

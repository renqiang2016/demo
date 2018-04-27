/*
package com.example.ren.service.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

*/
/**
 * security登陆认证
 *
 * @author qiang.ren
 * @date 2018/4/23
 *//*

@Component
public class MyUserDetailsService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户名：{}", username);
        String password = passwordEncoder.encode("123456");
        logger.info("加密密码：{}", password);
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
*/

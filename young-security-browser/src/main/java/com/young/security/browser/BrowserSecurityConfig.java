package com.young.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Youngni
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

//登录方式：表单登录（http.formLogin()），弹框登录（http.httpBasic()）
        http.formLogin()

            // 任何请求都需要身份认证
            .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    /**
     * 	声明密码加密解密 Bean
     * 	BCryptPasswordEncoder implements PasswordEncoder
     * 	encode : 加密方法
     * 	matches　：前台密码和数据库加密的密码作比较
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

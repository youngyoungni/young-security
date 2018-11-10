package com.young.security.browser;

import com.young.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
* @Description:    java类作用描述
* @Author:         YoungNi
* @CreateDate:     2018/11/10 15:40
* @UpdateDate:     2018/11/10 15:40
* @implNote
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler youngAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler youngAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                /*
                    登录页面过滤
                    .html 请求：跳转到login.html
                    hello 请求：返回JSON数据
                 */
                .loginPage("/authentication/require")
                //由 security的 "UsernamePasswordAuthenticationFilter" 来处理此请求
                .loginProcessingUrl("/authentication/form")
                .successHandler(youngAuthenticationSuccessHandler)
                .failureHandler(youngAuthenticationFailureHandler)
                //所有请求都需要身份认证
                .and()
                    .authorizeRequests()
                        //允许一下接口可以访问
                        .antMatchers(
                                "/com/young/security/browser/authentication/require"
                                ,securityProperties.getBrowser().getLoginPage()
                        )
                        .permitAll()
                    .anyRequest()
                    .authenticated()
                //关闭跨站请求伪造
                .and()
                    .csrf()
                    .disable();
    }

    /**
     * 	声明密码加密解密 Bean
     * 	BCryptPasswordEncoder implements PasswordEncoder
     * 	encode : 加密方法
     * 	matches　：前台密码和数据库加密的密码作比较
     */
    @Bean
    public PasswordEncoder passwrodEncoder() {
        return new BCryptPasswordEncoder();
    }
}

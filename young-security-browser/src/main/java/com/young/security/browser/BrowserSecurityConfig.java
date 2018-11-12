package com.young.security.browser;

import com.young.security.core.properties.SecurityProperties;
import com.young.security.core.validate.code.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
* @Description:   流浪器登录配置类
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
        /**
         * 声明验证码过滤器，设置好需要的资源
         */
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setYoungAuthenticationFailureHandler(youngAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

            //在验证 用户和密码 之前验证 “验证码”
        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                //设置登录方式（formLogin(表单),httpBasic(弹框)）
                .formLogin()
                /*
                    登录页面过滤
                    .html 请求：跳转到login.html
                    hello 请求：返回JSON数据
                 */
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                //成功
                .successHandler(youngAuthenticationSuccessHandler)
                //失败
                .failureHandler(youngAuthenticationFailureHandler)
                //所有请求都需要身份认证
                .and()
                    .authorizeRequests()
                        //允许一下接口可以访问
                        .antMatchers("/authentication/require"
                                ,securityProperties.getBrowser().getLoginPage()
                                ,"/code/image")
                        .permitAll()	//当访问 antMatchers() 这些个请求的时候，不需要身份验证
                    .anyRequest()
                    .authenticated()
                //Could not verify the provided CSRF token because your session was not found.
                //关闭 CSRF token
                .and()
                    .csrf()
                    .disable();

        //关闭跨站请求伪造
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

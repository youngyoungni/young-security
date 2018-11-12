package com.young.security.core.validate.code.filter;

import com.young.security.core.properties.SecurityProperties;
import com.young.security.core.validate.code.controller.ValidateCodeController;
import com.young.security.core.validate.code.exception.ValidateCodeException;
import com.young.security.core.validate.code.model.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
* @Description:    验证码的过滤器，每次请求都会过滤
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:05
* @UpdateDate:     2018/11/12 16:05
* @implNote  
* @UpdateRemark:   implements InitializingBean:等其他组件初始化完基本数据，再来初始化“urls”
* @Version:        1.0
*/
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AuthenticationFailureHandler youngAuthenticationFailureHandler;

    /**
     * 存放请求的URL
     */
    private Set<String> urls = new HashSet<String>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 等其他组件初始化完基本数据，再来初始化“urls”
     * 可配置验证码的拦截接口
     * @throws ServletException :
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCode().getImage().getUrl(),
                ","
        );
        /*
            配置 需要验证码的请求，使用 Set 集合将其封装
         */
        for (String url: configUrls) {
            urls.add(url);
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request
                        , HttpServletResponse response
                        , FilterChain filterChain) throws ServletException, IOException {
        String loginUrl = "/authentication/form";
        boolean action = false;
        /*
            对需要验证码过滤的请求，进行筛选
         */
        for (String url: urls) {
            System.out.println(url);
            // 如果配置的 url 能和 正在请求的url 对上，表明需要验证码过滤
            if ( pathMatcher.match(url, request.getRequestURI())){
                action = true;
            }
        }
        if(action){
            try{
                validateCode(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                youngAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                //如果发生异常就不再进行往下执行
                return;
            }

        }
        /**
         * 继续 security 下面的过滤器
         */
        filterChain.doFilter(request,response);
    }

    private void validateCode(ServletWebRequest request) throws ServletRequestBindingException {
        /**
         * 1.取得登录表单中提交 验证码值
         * 2.取得生成验证码中存在session的 验证码值
         */
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest()
                ,"imageCode");
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request
                ,ValidateCodeController.SESSION_KEY);

        /**
         * 3.判断
         */
        if( StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证不能为空");
        }
        // codeInSession == null 会报空指针
        if(Objects.equals(null,codeInSession.getCode() )){
            throw new ValidateCodeException("验证码不存在");
        }
        if( codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if( !StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getYoungAuthenticationFailureHandler() {
        return youngAuthenticationFailureHandler;
    }

    public void setYoungAuthenticationFailureHandler(AuthenticationFailureHandler youngAuthenticationFailureHandler) {
        this.youngAuthenticationFailureHandler = youngAuthenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}

package com.young.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.security.browser.support.SimpleResponse;
import com.young.security.core.properties.LoginType;
import com.young.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description:    验证失败类
* @Author:         YoungNi
* @CreateDate:     2018/11/10 15:48
* @UpdateDate:     2018/11/10 15:48
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Component("youngAuthenticationFailureHandler")
public class YoungAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 登录失败
     * @param request   :
     * @param response  :
     * @param exception :
     * @throws IOException  :
     * @throws ServletException :
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request
                                    , HttpServletResponse response
                                    , AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        if( LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request,response,exception);
        }


    }
}

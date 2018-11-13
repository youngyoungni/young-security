package com.young.security.core.validate.code.impl;

import com.young.security.core.properties.SecurityProperties;
import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.model.ImageCode;
import com.young.security.core.validate.code.model.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
* @Description:   短信验证码生成器
* @Author:         YoungNi
* @CreateDate:     2018/11/12 17:51
* @UpdateDate:     2018/11/12 17:51
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class SmsCodeGenerator implements ValidateCodeGenerator {

    /**
     * @Autowired   :
     */
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomAlphanumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }



    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}

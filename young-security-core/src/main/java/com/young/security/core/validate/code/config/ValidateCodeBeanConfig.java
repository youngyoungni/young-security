package com.young.security.core.validate.code.config;

import com.young.security.core.properties.SecurityProperties;
import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.impl.ImageCodeGenerator;
import com.young.security.core.validate.code.impl.SmsCodeGenerator;
import com.young.security.core.validate.code.sms.SmsCodeSender;
import com.young.security.core.validate.code.sms.impl.DefaultSmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Description:   验证码配置类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 18:31
* @UpdateDate:     2018/11/12 18:31
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *  加“@Bean”也可在 ImageCodeGenerator类上声明 “@Component”，并指定名称
     @ConditionalOnMissingBean(name = "imageCodeGenerator") :
        如果spring容器中有了，就直接使用，没有才创建
     * @return  :ImageCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    /**
     *  首先会在应用中找 “SmsCodeSender.class”类的实现
     *  如果没有就“new DefaultSmsCodeSender()”
     *  @return  :   DefaultSmsCodeSender
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }

}

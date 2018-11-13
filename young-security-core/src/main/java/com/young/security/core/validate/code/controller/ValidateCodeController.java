package com.young.security.core.validate.code.controller;

import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.impl.ImageCodeGenerator;
import com.young.security.core.validate.code.model.ImageCode;
import com.young.security.core.validate.code.model.ValidateCode;
import com.young.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description:    验证码生成控制器
* @Author:         YoungNi
* @CreateDate:     2018/11/12 15:40
* @UpdateDate:     2018/11/12 15:40
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     *  验证码生成器：图形/短信
     *      图形：通过 ValidateCodeBeanConfig 中的 @Bean imageCodeGenerator
     *      短信：通过 ValidateCodeBeanConfig 中的 @Bean smsCodeGenerator
     *      短信服务商：通过 ValidateCodeBeanConfig 中的 @Bean smsCodeSender
     *  进行配置......
     */
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private SmsCodeSender smsCodeSender;

    @RequestMapping( value = "/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         *  1.生成验证码
         *  2.将验证码存入Session
         *  3.输出验证码到页面
         */
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    @RequestMapping( value = "/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        /*
         *  1.生成验证码
         *  2.将验证码存入Session
         *  3.通过短信服务商发送验证码
         */
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }


}

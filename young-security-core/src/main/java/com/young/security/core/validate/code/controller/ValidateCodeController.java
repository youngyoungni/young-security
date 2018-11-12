package com.young.security.core.validate.code.controller;

import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.impl.ImageCodeGenerator;
import com.young.security.core.validate.code.model.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
     *  imageCodeGenerator：ValidateCodeBeanConfig/imageCodeGenerator 方法一致
     */
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;


    @RequestMapping( value = "/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         *  1.生成验证码
         *  2.将验证码存入Session
         *  3.输出验证码到页面
         */
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageCode s = (ImageCode)sessionStrategy.getAttribute(new ServletWebRequest(request),SESSION_KEY);
        System.out.println(s.getCode());
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }


}

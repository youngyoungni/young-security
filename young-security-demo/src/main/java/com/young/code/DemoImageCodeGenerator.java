package com.young.code;

import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.model.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
/**
* @Description:   测试：@Component("imageCodeGenerator")
* @Author:         YoungNi
* @CreateDate:     2018/11/12 18:42
* @UpdateDate:     2018/11/12 18:42
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    /*
        加在类名上：@Component("imageCodeGenerator") 注释了，就会使用 core 模块中的 Bean
     */

    @Override
    public ImageCode generate(ServletWebRequest request) {
        /**
         * 如果在 使用安全框架（spring security）中定义此类，并返回了 null 就会 报空指针
         */
        System.out.println("Demo 项目中：生成图形验证码");
        return null;
    }
}

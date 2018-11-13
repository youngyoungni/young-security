package com.young.security.core.validate.code;

import com.young.security.core.validate.code.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
* @Description:   验证码生成器
* @Author:         YoungNi
* @CreateDate:     2018/11/12 17:49
* @UpdateDate:     2018/11/12 17:49
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @param request   ：包装了 request/response
     * @return          ：
     */
    ValidateCode generate(ServletWebRequest request);
}

package com.young.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
* @Description:   校验码处理接口，封装不同的校验码的处理逻辑
* @Author:         YoungNi
* @CreateDate:     2018/11/13 14:28
* @UpdateDate:     2018/11/13 14:28
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface ValidateCodeProcessor {

    /**
     * 校验码放入 session 的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     *  创建校验码
     * @param request   : 封装 request/response
     */
    void create(ServletWebRequest request);
}

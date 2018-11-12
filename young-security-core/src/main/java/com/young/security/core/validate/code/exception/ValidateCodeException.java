package com.young.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
* @Description:    验证码异常类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:22
* @UpdateDate:     2018/11/12 16:22
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

package com.young.security.core.validate.code.sms.impl;

import com.young.security.core.validate.code.sms.SmsCodeSender;

/**
* @Description:    默认的短信验证码实现类
* @Author:         YoungNi
* @CreateDate:     2018/11/13 13:56
* @UpdateDate:     2018/11/13 13:56
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机（"+ mobile +"）发送短信验证码：" + code );
    }
}

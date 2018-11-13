package com.young.security.core.validate.code.sms;

/**
* @Description:   短信发送接口
* @Author:         YoungNi
* @CreateDate:     2018/11/13 13:53
* @UpdateDate:     2018/11/13 13:53
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface SmsCodeSender {

    /**
     *  发送短信
     * @param mobile    ：手机号
     * @param code      ：验证码
     */
    void send(String mobile, String code);
}

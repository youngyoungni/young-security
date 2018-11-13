package com.young.security.core.validate.code.model;

import java.time.LocalDateTime;

/**
* @Description:    验证码实体类(短信)
* @Author:         YoungNi
* @CreateDate:     2018/11/12 15:42
* @UpdateDate:     2018/11/12 15:42
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ValidateCode {

    /**
     * 验证码：随机数
     */
    private String code;
    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
    /**
     * 判断是否过期
     * @return  ：boolean
     */
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}

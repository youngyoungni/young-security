package com.young.security.core.properties;

/**
* @Description:    短信验证码配置类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:52
* @UpdateDate:     2018/11/12 16:52
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class SmsCodeProperties{

    private int length = 6;
    /**
     * 图片验证码的过期时间
     */
    private int expireIn = 60;

    /**
     * url ：访问以“,”逗号隔开的URL地址都需要验证码，才能访问
     */
    private String url ;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

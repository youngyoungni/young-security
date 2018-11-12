package com.young.security.core.properties;

/**
* @Description:    图片验证码配置类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:52
* @UpdateDate:     2018/11/12 16:52
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ImageCodeProperties {

    /**
     * 图片验证码的宽度
     */
    private int width = 67;
    /**
     * 图片验证码的高度
     */
    private int height = 23;
    /**
     * 验证码数的长度
     */
    private int length = 4;
    /**
     * 图片验证码的过期时间
     */
    private int expireIn = 60;

    /**
     * url ：访问以“,”逗号隔开的URL地址都需要验证码，才能访问
     */
    private String url ;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

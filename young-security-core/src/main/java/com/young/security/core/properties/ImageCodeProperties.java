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
public class ImageCodeProperties extends SmsCodeProperties{

    /**
     * 图片验证码的宽度
     */
    private int width = 67;
    /**
     * 图片验证码的高度
     */
    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }

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

}

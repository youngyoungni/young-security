package com.young.security.core.properties;
/**
* @Description:   验证码配置类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:55
* @UpdateDate:     2018/11/12 16:55
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}

package com.young.security.core.validate.code.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
* @Description:    图形验证码实体类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 15:42
* @UpdateDate:     2018/11/12 15:42
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ImageCode extends ValidateCode{

    /**
     * 图片
     */
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}

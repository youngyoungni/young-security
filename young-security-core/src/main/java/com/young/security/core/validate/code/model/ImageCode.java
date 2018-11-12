package com.young.security.core.validate.code.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
* @Description:    验证码实体类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 15:42
* @UpdateDate:     2018/11/12 15:42
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ImageCode {

    /**
     * 图片
     */
    private BufferedImage image;
    /**
     * 验证码：随机数
     */
    private String code;
    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }
    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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

package com.young.security.core.validate.code.impl;

import com.young.security.core.properties.SecurityProperties;
import com.young.security.core.validate.code.ValidateCodeGenerator;
import com.young.security.core.validate.code.model.ImageCode;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
* @Description:   图形验证码生成器
* @Author:         YoungNi
* @CreateDate:     2018/11/12 17:51
* @UpdateDate:     2018/11/12 17:51
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/

public class ImageCodeGenerator implements ValidateCodeGenerator {


    /**
     * @Autowired   :
     */
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
    /**
     * 	从请求域中获取参数为 “width” 的值，如果没有就取 属性配置的的值
     */
        int width = ServletRequestUtils.getIntParameter(request.getRequest()
                ,"width"
                ,securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest()
                ,"height"
                ,securityProperties.getCode().getImage().getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));

        g.setColor(getRandColor(180, 240));
        for( int i = 0 ; i < 80 ; i++ ) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(8);
            int yl = random.nextInt(8);
            g.drawLine(x, y, x+xl, y+yl);
        }

        String sRand = "";
        for(int i = 0 ; i < securityProperties.getCode().getImage().getLength() ; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color( 20+random.nextInt(110)
                    , 20+random.nextInt(110)
                    , 20+random.nextInt(110)
                    , 20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 16);
        }

        g.dispose();

        /**
         * 验证码图片，随机数，有效期
         */
        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * 生成随机的背景条纹
     * @param fc    :
     * @param bc    :
     * @return      :   Color
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if( fc > 255) {
            fc = 255;
        }
        if( bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}

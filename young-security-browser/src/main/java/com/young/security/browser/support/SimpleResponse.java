package com.young.security.browser.support;

/**
* @Description:   JSON 数据返回类
* @Author:         YoungNi
* @CreateDate:     2018/11/12 16:38
* @UpdateDate:     2018/11/12 16:38
* @implNote  
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class SimpleResponse {

    private Object content ;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

package com.young.security.core.properties;

/**
 * @author Youngni
 */
public class BrowserProperties {

    /**
     * 登录的默认页面
     */
    private String loginPage = "/young-singIn.html";

    /**
     * 登录返回数据的格式
     */
    private LoginType loginType = LoginType.JSON;



    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}

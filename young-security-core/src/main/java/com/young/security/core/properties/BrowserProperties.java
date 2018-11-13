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

    /**
     * 记住密码的过期时长
     * 3600 ： 一小时
     */
    private int rememberMeSeconds = 3600;

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

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }
    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}

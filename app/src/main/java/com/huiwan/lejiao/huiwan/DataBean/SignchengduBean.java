package com.huiwan.lejiao.huiwan.DataBean;

public class SignchengduBean {
    private String user ="";
    private String password="";
    private String type="";
    private String sign="";


    public SignchengduBean(String account, String password, String type, String sign) {
        this.user = account;
        this.password = password;
        this.type = type;
        this.sign = sign;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

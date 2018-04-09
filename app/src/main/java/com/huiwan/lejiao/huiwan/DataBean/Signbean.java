package com.huiwan.lejiao.huiwan.DataBean;


//登陆名和密码对象
public class Signbean {
    private String account="";
    private String password="";
    private String status="";
    private String lasttime ="";
    private String phone="";

    public Signbean(String account, String password, String status, String userid, String phone) {
        super();
        this.account = account;
        this.password = password;
        this.status = status;
        this.lasttime = userid;
        this.phone = phone;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setSex(String status) {
        this.status = status;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

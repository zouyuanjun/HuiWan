package com.huiwan.lejiao.huiwan.DataBean;


import com.huiwan.lejiao.huiwan.control.StaticValue;

//登陆名和密码对象
public class Signbean {
    String name;
    String password;

    public Signbean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

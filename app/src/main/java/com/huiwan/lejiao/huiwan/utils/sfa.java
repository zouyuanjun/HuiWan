package com.huiwan.lejiao.huiwan.utils;

public class sfa {

    private String name;
    private String phone;
    private String sp1name;
    private String sp2name;
    private String sp1phone;
    private String sp3name;

    public sfa(String name, String phone, String sp1name, String sp2name, String sp1phone, String sp3name) {
        this.name = name;
        this.phone = phone;
        this.sp1name = sp1name;
        this.sp2name = sp2name;
        this.sp1phone = sp1phone;
        this.sp3name = sp3name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSp1name() {
        return sp1name;
    }

    public void setSp1name(String sp1name) {
        this.sp1name = sp1name;
    }

    public String getSp2name() {
        return sp2name;
    }

    public void setSp2name(String sp2name) {
        this.sp2name = sp2name;
    }

    public String getSp1phone() {
        return sp1phone;
    }

    public void setSp1phone(String sp1phone) {
        this.sp1phone = sp1phone;
    }

    public String getSp3name() {
        return sp3name;
    }

    public void setSp3name(String sp3name) {
        this.sp3name = sp3name;
    }
}


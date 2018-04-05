package com.huiwan.lejiao.huiwan.DataBean;

public class HomePagedataBean {
    String name;
    String lv;               //等级
    String jrmubiao;        //今日目标
    String jrrenshu;        //今日人数
    String bymubiao;        //本月目标
    String byrenshu;        //本月人数
    String userid;

    public HomePagedataBean(String name, String lv, String jrmubiao, String jrrenshu, String bymubiao, String byrenshu, String userid) {
        this.name = name;
        this.lv = lv;
        this.jrmubiao = jrmubiao;
        this.jrrenshu = jrrenshu;
        this.bymubiao = bymubiao;
        this.byrenshu = byrenshu;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getJrmubiao() {
        return jrmubiao;
    }

    public void setJrmubiao(String jrmubiao) {
        this.jrmubiao = jrmubiao;
    }

    public String getJrrenshu() {
        return jrrenshu;
    }

    public void setJrrenshu(String jrrenshu) {
        this.jrrenshu = jrrenshu;
    }

    public String getBymubiao() {
        return bymubiao;
    }

    public void setBymubiao(String bymubiao) {
        this.bymubiao = bymubiao;
    }

    public String getByrenshu() {
        return byrenshu;
    }

    public void setByrenshu(String byrenshu) {
        this.byrenshu = byrenshu;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

package com.huiwan.lejiao.huiwan.DataBean;

import com.facebook.drawee.view.SimpleDraweeView;

public class UserdataBean {
    String id;
    String name;
    String lv;
    String jinrimubiao;
    String jinrirenshu;
    String benyuerenshu;
    String benyuemubiao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJinrimubiao() {
        return jinrimubiao;
    }

    public void setJinrimubiao(String jinrimubiao) {
        this.jinrimubiao = jinrimubiao;
    }

    public String getJinrirenshu() {
        return jinrirenshu;
    }

    public void setJinrirenshu(String jinrirenshu) {
        this.jinrirenshu = jinrirenshu;
    }

    public String getBenyuerenshu() {
        return benyuerenshu;
    }

    public void setBenyuerenshu(String benyuerenshu) {
        this.benyuerenshu = benyuerenshu;
    }

    public String getBenyuemubiao() {
        return benyuemubiao;
    }

    public void setBenyuemubiao(String benyuemubiao) {
        this.benyuemubiao = benyuemubiao;
    }

    public UserdataBean(String id, String name, String lv, String jinrimubiao, String jinrirenshu, String benyuerenshu, String benyuemubiao) {
        this.id = id;
        this.name = name;
        this.lv = lv;
        this.jinrimubiao = jinrimubiao;
        this.jinrirenshu = jinrirenshu;
        this.benyuerenshu = benyuerenshu;
        this.benyuemubiao = benyuemubiao;

    }
}

package com.huiwan.lejiao.huiwan.DataBean;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;

public class UsermubiaoBean implements Serializable {

    int jrmubiao;   //今日目标
    int jrrenshu;//今日人数
    int byrenshu;//本月人数
    int bymubiao;//本月目标
    String phone;
    LowerMgr lowerMgr; //下级结构体

    public UsermubiaoBean(int jrmubiao, int jrrenshu, int byrenshu, int byemubiao, String phone, LowerMgr lowerMgr) {
        this.jrmubiao = jrmubiao;
        this.jrrenshu = jrrenshu;
        this.byrenshu = byrenshu;
        this.bymubiao = byemubiao;
        this.phone = phone;
        this.lowerMgr = lowerMgr;
    }

    public int getJrmubiao() {
        return jrmubiao;
    }

    public void setJrmubiao(int jrmubiao) {
        this.jrmubiao = jrmubiao;
    }

    public int getJrrenshu() {
        return jrrenshu;
    }

    public void setJrrenshu(int jrrenshu) {
        this.jrrenshu = jrrenshu;
    }

    public int getByrenshu() {
        return byrenshu;
    }

    public void setByrenshu(int byrenshu) {
        this.byrenshu = byrenshu;
    }

    public int getBymubiao() {
        return bymubiao;
    }

    public void setBymubiao(int byemubiao) {
        this.bymubiao = byemubiao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LowerMgr getLowerMgr() {
        return lowerMgr;
    }

    public void setLowerMgr(LowerMgr lowerMgr) {
        this.lowerMgr = lowerMgr;
    }
}

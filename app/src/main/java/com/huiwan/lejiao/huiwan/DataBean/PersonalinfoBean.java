package com.huiwan.lejiao.huiwan.DataBean;

import java.io.Serializable;

public class PersonalinfoBean implements Serializable {
    String name;
    String phonenum;
    String weichat;
    String photourl;
    int keyongma;

    public PersonalinfoBean(String name, String phonenum, String weichat, String photourl, int keyongma) {
        this.name = name;
        this.phonenum = phonenum;
        this.weichat = weichat;
        this.photourl = photourl;
        this.keyongma = keyongma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getWeichat() {
        return weichat;
    }

    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public int getKeyongma() {
        return keyongma;
    }
    public void setKeyongma(int keyongma) {
        this.keyongma = keyongma;
    }
}

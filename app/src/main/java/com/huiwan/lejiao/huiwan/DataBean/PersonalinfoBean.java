package com.huiwan.lejiao.huiwan.DataBean;

public class PersonalinfoBean {
    String name;
    String phonenum;
    String weichat;
    String photourl;
    String keyongma;

    public PersonalinfoBean(String name, String phonenum, String weichat, String photourl, String keyongma) {
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

    public String getKeyongma() {
        return keyongma;
    }

    public void setKeyongma(String keyongma) {
        this.keyongma = keyongma;
    }
}

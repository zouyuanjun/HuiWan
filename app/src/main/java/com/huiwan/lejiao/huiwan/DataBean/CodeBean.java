package com.huiwan.lejiao.huiwan.DataBean;

public class CodeBean {
    String code;
    String beizhu;
    String creattime;
    String usetime;
    String isuse;

    public CodeBean(String code, String beizhu, String creattime, String usetime, String isuse) {
        this.code = code;
        this.beizhu = beizhu;
        this.creattime = creattime;
        this.usetime = usetime;
        this.isuse = isuse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }
}

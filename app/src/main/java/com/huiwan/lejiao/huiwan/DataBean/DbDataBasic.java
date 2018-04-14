package com.huiwan.lejiao.huiwan.DataBean;

public class DbDataBasic {

    private String name = "";            //姓名
    private String phone = "";           //手机号(唯一标识)
    private String idcard = "";          //身份证号
    private String weixin = "";          //微信
    private String taobao = "";          //淘宝
    private String address = "";         //地址
    private String birthdate = "";       //生日
    private int lever = 0;           //等级
    private String exphone = "";         //上级手机号
    private int codenum = 0;		 //可生成注册码数量
    private String account = "";

    public int getCodenum() {
        return codenum;
    }

    public void setCodenum(int codenum) {
        this.codenum = codenum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public DbDataBasic() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setPhonenum(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getTaobao() {
        return taobao;
    }

    public void setTaobao(String taobao) {
        this.taobao = taobao;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }


    public String getExphone() {
        return exphone;
    }

    public void setExphone(String exphone) {
        this.exphone = exphone;
    }

}

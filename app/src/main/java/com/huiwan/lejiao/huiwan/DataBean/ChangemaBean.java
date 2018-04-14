package com.huiwan.lejiao.huiwan.DataBean;

public class ChangemaBean {
	  private String fromphone=""; //转出手机号
	   private String tophone="";  //转入手机号
	   private int codenum=0;  //转入数量

	public ChangemaBean(String fromphone, String tophone, int codenum) {
		this.fromphone = fromphone;
		this.tophone = tophone;
		this.codenum = codenum;
	}


	public String getFromphone() {
		return fromphone;
	}
	public void setFromphone(String fromphone) {
		this.fromphone = fromphone;
	}
	public String getTophone() {
		return tophone;
	}
	public void setTophone(String tophone) {
		this.tophone = tophone;
	}
	public int getCodenum() {
		return codenum;
	}
	public void setCodenum(int codenum) {
		this.codenum = codenum;
	}
	   
}

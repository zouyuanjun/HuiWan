package com.huiwan.lejiao.huiwan.DataBean;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class CodeinfoBean implements Comparable<CodeinfoBean>, Serializable {
    private String phone = "";  
    private String code = "";  
    private String generate = "";  
    private String usedate = "";
  
    public CodeinfoBean() {
		
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGenerate() {
		return generate;
	}

	public void setGenerate(String generate) {
		this.generate = generate;
	}

	public String getUsedate() {
		return usedate;
	}

	public void setUsedate(String usedate) {
		this.usedate = usedate;
	}

	@Override
	public int compareTo(@NonNull CodeinfoBean o) {
		return 0;
	}
}

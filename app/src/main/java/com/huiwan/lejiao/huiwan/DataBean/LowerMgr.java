package com.huiwan.lejiao.huiwan.DataBean;

// 下级字段维护类;
public class LowerMgr {
	
    private int nLower = 0;  			// 直系数量;
    private int nTeam = 0;  			// 除直系以外的团队数量;
    private int nEqual = 0;  			// 平级直系的数量;(用来判断升级)
    
	public LowerMgr()
	{
		
	}

	public LowerMgr(int nLower, int nTeam, int nEqual) {
		this.nLower = nLower;
		this.nTeam = nTeam;
		this.nEqual = nEqual;
	}

	public int getnLower() {
		return nLower;
	}

	public void setnLower(int nLower) {
		this.nLower = nLower;
	}

	public int getnTeam() {
		return nTeam;
	}

	public void setnTeam(int nTeam) {
		this.nTeam = nTeam;
	}

	public int getnEqual() {
		return nEqual;
	}

	public void setnEqual(int nEqual) {
		this.nEqual = nEqual;
	}
	
	
}

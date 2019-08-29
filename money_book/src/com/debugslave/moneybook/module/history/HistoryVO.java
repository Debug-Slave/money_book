package com.debugslave.moneybook.module.history;

import java.util.Date;

public class HistoryVO {
	
	private int hIdx = 0;
	private int userIdx = 0;
	private String hisTitle = "";
	private String hisType = "";
	private String cateCode = "";
	private String cateName = "";
	private int hisMoney = 0;
	private String hisMemo = "";
	private Date hisDate = null;
	private int hisStat=0;
	private Date regDate = null;
	
	public int gethIdx() {
		return hIdx;
	}
	public void sethIdx(int hIdx) {
		this.hIdx = hIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public String getHisTitle() {
		return hisTitle;
	}
	public void setHisTitle(String hisTitle) {
		this.hisTitle = hisTitle;
	}
	public String getHisType() {
		return hisType;
	}
	public void setHisType(String hisType) {
		this.hisType = hisType;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getHisMoney() {
		return hisMoney;
	}
	public void setHisMoney(int hisMoney) {
		this.hisMoney = hisMoney;
	}
	public String getHisMemo() {
		return hisMemo;
	}
	public void setHisMemo(String hisMemo) {
		this.hisMemo = hisMemo;
	}
	public Date getHisDate() {
		return hisDate;
	}
	public void setHisDate(Date hisDate) {
		this.hisDate = hisDate;
	}
	public int getHisStat() {
		return hisStat;
	}
	public void setHisStat(int hisStat) {
		this.hisStat = hisStat;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDt(Date regDt) {
		this.regDate = regDt;
	}
}

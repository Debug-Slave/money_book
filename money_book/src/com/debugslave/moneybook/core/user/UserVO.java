package com.debugslave.moneybook.core.user;

import java.util.Date;

public class UserVO {
	
	private int userIdx = 0;
	private String userName = "";
	private String userId  = "";
	private String userPassword  = "";
	private String passSalt = "";
	private String userConfirmPassword = "";
	private Date regDt = null;
	private Date recentLoginDt = null;
	private int userStat = 1;
	
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}
	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getRecentLoginDt() {
		return recentLoginDt;
	}
	public void setRecentLoginDt(Date recentLoginDt) {
		this.recentLoginDt = recentLoginDt;
	}
	public void setUserStat(int userStat) {
		this.userStat = userStat;
	}
	public int getUserStat() {
		return userStat;
	}
	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}
	public String getPassSalt() {
		return passSalt;
	}
}

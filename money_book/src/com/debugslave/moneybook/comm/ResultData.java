package com.debugslave.moneybook.comm;

import org.springframework.http.HttpStatus;

public class ResultData {
	
	
	private String msg = "알수 없는 오류가 발생하였습니다. \\n동일한 문제가 계속 발생하면 개발자에게 문의해주세요.";
	private HttpStatus resultCode = null;
	
	
	public ResultData (HttpStatus resultCode) {
		this.resultCode = resultCode;
	}
	
	public ResultData (String msg, HttpStatus resultCode) {
		this.msg = msg;
		this.resultCode = resultCode;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HttpStatus getResultCode() {
		return resultCode;
	}
	public void setResultCode(HttpStatus resultCode) {
		this.resultCode = resultCode;
	} 
	
}

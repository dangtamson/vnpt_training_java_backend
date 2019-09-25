package com.data.mysql.model;

public class ReturnResultSetModel {
	public String CODE;
	public String MESSAGE;
	public String RESULT;
	
	public ReturnResultSetModel(String sCode, String sMessage, String sResult) {
		this.CODE = sCode;
		this.MESSAGE =sMessage;
		this.RESULT = sResult;
	}
}

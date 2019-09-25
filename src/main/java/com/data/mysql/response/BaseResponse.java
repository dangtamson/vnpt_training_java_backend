package com.data.mysql.response;

public class BaseResponse {
	public String CODE;
	public String MESSAGE;
	public Object RESULT;
	
	public BaseResponse() {}
	
	public BaseResponse(String sCode, String sMessage, Object oResult) {
		this.CODE = sCode;
		this.MESSAGE = sMessage;
		this.RESULT = oResult;
	}
	
}

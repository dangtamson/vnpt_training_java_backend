package com.data.mysql.request;

import java.util.List;

public class DataRequest {
	private String Cmd;
	private String Type;
	private List<DataRequestParam> Params;
	
	public String getCmd() {
		return Cmd;
	}
	public void setCmd(String cmd) {
		Cmd = cmd;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public List<DataRequestParam> getParams() {
		return Params;
	}
	public void setParams(List<DataRequestParam> params) {
		Params = params;
	}

}


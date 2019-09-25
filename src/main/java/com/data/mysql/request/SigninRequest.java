package com.data.mysql.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SigninRequest {
	@JsonProperty("extradata")
	private String extradata;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;

	public String getExtradata() {
		return extradata;
	}
	public void setExtradata(String extradata) {
		this.extradata = extradata;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

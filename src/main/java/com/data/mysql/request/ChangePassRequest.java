package com.data.mysql.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePassRequest {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password_old")
	private String password_old;
	@JsonProperty("password_new")
	private String password_new;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword_old() {
		return password_old;
	}
	public void setPassword_old(String password_old) {
		this.password_old = password_old;
	}
	public String getPassword_new() {
		return password_new;
	}
	public void setPassword_new(String password_new) {
		this.password_new = password_new;
	}

	public String valid() {
		String sRs = "";
		
		if (this.password_new.length()<3) {
			sRs +="Invalid Password Length;";
		}
		
		return sRs;
	}
}

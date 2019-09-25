package com.data.mysql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Value("${app.name}")
	private String AppName;
	@Value("${app.code}")
	private String Code;
	@Value("${app.owner}")
	private String Owner;
	public String getAppName() {
		return AppName;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	
	
}

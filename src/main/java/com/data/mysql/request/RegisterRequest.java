package com.data.mysql.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
	
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("fullname")
	private String fullname;
	@JsonProperty("email")
	private String email;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("address")
	private String address;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("job")
	private String job;
	@JsonProperty("extradata")
	private String extradata;
	
	
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getExtradata() {
		return extradata;
	}
	public void setExtradata(String extradata) {
		this.extradata = extradata;
	}
	
	public String valid() {
		String sRs = "";
		if (this.username.length()<3) {
			sRs +="Invalid Username;";
		}
		
		if (this.password.length()<3) {
			sRs +="Invalid Password;";
		}
		
		if (this.phone.length()<9 || this.phone.length()>11) {
			sRs +="Invalid PhoneNumber;";
		}
		
		if (!this.sex.equals("1") & !this.sex.equals("0") & !this.sex.equals("2") ) {
			sRs +="Invalid Sex;";
		}
		
		return sRs;
	}
	
}

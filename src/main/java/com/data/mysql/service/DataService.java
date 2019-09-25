package com.data.mysql.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.mysql.config.DataConfig;
import com.data.mysql.request.ChangePassRequest;
import com.data.mysql.request.RegisterRequest;
import com.data.mysql.response.BaseResponse;
import com.data.mysql.response.SigninResponse;
import com.data.mysql.utility.MySQLUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DataService {
	
	private DataConfig dataConfig;

	public DataService(DataConfig dataConfig) {
		super();
		this.dataConfig = dataConfig;
	}
	
	/*
	 * public BaseResponse register(RegisterRequest oRequest) throws Exception {
	 * BaseResponse oRsp = new BaseResponse(); try { MySQLUtil oUtil = new
	 * MySQLUtil(dataConfig); ObjectMapper mapper = new ObjectMapper();
	 * mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
	 * Map<String, Object> mParams = new HashMap<String, Object>();
	 * mParams.put("i_username", oRequest.getUsername()); mParams.put("i_password",
	 * getMD5(oRequest.getPassword())); mParams.put("i_fullname",
	 * oRequest.getFullname()); mParams.put("i_email", oRequest.getEmail());
	 * mParams.put("i_phone", oRequest.getPhone()); mParams.put("i_address",
	 * oRequest.getAddress()); mParams.put("i_sex", oRequest.getSex());
	 * mParams.put("i_job", oRequest.getJob()); mParams.put("i_extradata",
	 * oRequest.getExtradata()); oRsp =
	 * oUtil.callStoreProcedureBaseResponse("sp_account_register", mParams); } catch
	 * (Exception e) { throw e; } return oRsp; }
	 */
	

	

}

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


public class AccountService {
	
	private DataConfig dataConfig;

	public AccountService(DataConfig dataConfig) {
		super();
		this.dataConfig = dataConfig;
	}
	
	public BaseResponse changePass(ChangePassRequest oRequest) throws Exception {
		BaseResponse oRsp = new BaseResponse();
		try {
			MySQLUtil oUtil = new MySQLUtil(dataConfig);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			Map<String, Object> mParams = new HashMap<String, Object>();
			mParams.put("i_username", oRequest.getUsername());
			mParams.put("i_password_old", getMD5(oRequest.getPassword_old()));
			mParams.put("i_password_new", getMD5(oRequest.getPassword_new()));
			oRsp = oUtil.callStoreProcedureBaseResponse("sp_account_changepass", mParams);
		} catch (Exception e) {
			throw e;
		}
		return oRsp;
	}

	public BaseResponse register(RegisterRequest oRequest) throws Exception {
		BaseResponse oRsp = new BaseResponse();
		try {
			MySQLUtil oUtil = new MySQLUtil(dataConfig);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			Map<String, Object> mParams = new HashMap<String, Object>();
			mParams.put("i_username", oRequest.getUsername());
			mParams.put("i_password", getMD5(oRequest.getPassword()));
			mParams.put("i_fullname", oRequest.getFullname());
			mParams.put("i_email", oRequest.getEmail());
			mParams.put("i_phone", oRequest.getPhone());
			mParams.put("i_address", oRequest.getAddress());
			mParams.put("i_sex", oRequest.getSex());
			mParams.put("i_job", oRequest.getJob());
			mParams.put("i_extradata", oRequest.getExtradata());
			oRsp = oUtil.callStoreProcedureBaseResponse("sp_account_register", mParams);
		} catch (Exception e) {
			throw e;
		}
		return oRsp;
	}
	

	public SigninResponse signIn(String sUsername, String sPassword, String sExtraData) throws Exception {
		
		SigninResponse oRsp = null;
		MySQLUtil oUtil = new MySQLUtil(dataConfig);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Map<String, Object> mParams=new HashMap<String, Object>();
		mParams.put("i_user", sUsername);
		mParams.put("i_pass", getMD5(sPassword));
		mParams.put("i_extradata", sExtraData);
		try {

			String sJson = oUtil.callStoreProcedure2Json("sp_vnpt_account_signin",mParams);
			 List<SigninResponse> list = mapper.readValue(sJson, new
			 TypeReference<List<SigninResponse>>() { }); if (list.size() > 0) { oRsp =list.get(0); }

		} catch (Exception e) {
			throw e;
		}
		return oRsp;
	}
	
	private String convertByteToHex1(byte[] data) {
		  BigInteger number = new BigInteger(1, data);
		  String hashtext = number.toString(16);
		  while (hashtext.length() < 32) {
		    hashtext = "0" + hashtext;
		  }
		  return hashtext;
		}
	
	private  String getMD5(String input) {
		  try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] messageDigest = md.digest(input.getBytes());
		    return convertByteToHex1(messageDigest);
		  } catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		  }
		}

}

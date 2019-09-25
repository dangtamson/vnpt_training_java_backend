package com.data.mysql.api;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.mysql.config.DataConfig;
import com.data.mysql.request.RegisterRequest;
import com.data.mysql.request.SigninRequest;
import com.data.mysql.response.BaseResponse;
import com.data.mysql.response.SigninResponse;
import com.data.mysql.security.TokenAuthenticationService;
import com.data.mysql.service.AccountService;

@RestController
@RequestMapping("api/v1/public/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenApi {
	
	@Autowired
	private DataConfig dataConfig;
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<Object> signin(@RequestBody SigninRequest pRequest) {
		try {
			
			AccountService oService = new AccountService(dataConfig);
			SigninResponse oResp = oService.signIn(pRequest.getUsername(), pRequest.getPassword(), pRequest.getExtradata());
			if (oResp != null) {
				oResp.TokenType = "Bearer";
				oResp.ExtraData = Base64.getEncoder().withoutPadding().encodeToString(oResp.ExtraData.getBytes());
				oResp.Token = TokenAuthenticationService.getNewToken(pRequest.getUsername(),pRequest.getExtradata());
				BaseResponse oBaseResponse = new BaseResponse("0", "Đăng nhập thành công", oResp); 
				return new ResponseEntity<Object>(oBaseResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new BaseResponse("1", "Đăng nhập thất bại", null), HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(new BaseResponse("1", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody RegisterRequest pRequest) {
		try {
			if (!pRequest.valid().equals("")) {
				return new ResponseEntity<Object>(new BaseResponse("1",pRequest.valid(),null), HttpStatus.OK);
			}
			
			AccountService oService = new AccountService(dataConfig);
			return new ResponseEntity<Object>(oService.register(pRequest), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Object>(new BaseResponse("1", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

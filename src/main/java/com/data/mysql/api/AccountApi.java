package com.data.mysql.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.mysql.config.DataConfig;
import com.data.mysql.request.ChangePassRequest;
import com.data.mysql.response.BaseResponse;
import com.data.mysql.security.TokenAuthenticationService;
import com.data.mysql.service.AccountService;

@RestController
@RequestMapping("api/v1.0/account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountApi {
	
	@Autowired
	private DataConfig dataConfig;
	
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public ResponseEntity<Object> changePass(@RequestBody ChangePassRequest oRequest) {
		
		try {
			String sUsernameJwt = TokenAuthenticationService.getUsernameFromJWT();
			AccountService oServ = new AccountService(dataConfig);
			if (!oRequest.getUsername().equals(sUsernameJwt)) {
				return new ResponseEntity<Object>(new BaseResponse("1", "Bad request", oRequest.getUsername()), HttpStatus.BAD_REQUEST);
			} 
			
			if (!oRequest.valid().equals("")) {
				return new ResponseEntity<Object>(new BaseResponse("1",oRequest.valid(),null), HttpStatus.OK);
			}

			return new ResponseEntity<Object>(oServ.changePass(oRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new BaseResponse("1", e.getMessage(), null), HttpStatus.OK);
		}
	}
}

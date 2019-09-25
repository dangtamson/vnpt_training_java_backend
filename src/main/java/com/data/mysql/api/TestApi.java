package com.data.mysql.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.mysql.config.DataConfig;
import com.data.mysql.config.DataSourceListConfig;
import com.data.mysql.utility.MySQLUtil;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/v1.0/public/test")
public class TestApi {
	
	@Autowired
	private DataConfig dataConfig;
	
	@Autowired
	private DataSourceListConfig dataSourceListConfig;
	
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public Object healthcheck() {
		return "OK";
	}
	
	@RequestMapping(value = "/callprocedure", method = RequestMethod.POST)
	public Object callprocedure() {
		Object _tk = null;
		MySQLUtil _util = new MySQLUtil(dataConfig);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Map<String, Object> mParams=new HashMap<String, Object>();
		mParams.put("i_user", "ADMIN");
		mParams.put("i_pass", "123");
		mParams.put("i_ext", "");
		mParams.put("i_ipaddress", "127.0.0.1");
		try {

			String _json = _util.callStoreProcedure2Json("sp_account_signin",mParams);
			/*
			 * List<ComAccount> _lsAsmAccount = mapper.readValue(_json, new
			 * TypeReference<List<ComAccount>>() { }); if (_lsAsmAccount.size() > 0) { _tk =
			 * _lsAsmAccount.get(0); }
			 */
			_tk = _json;

		} catch (Exception e) {
			return e;
		}
		return _tk;
	}
	
	@RequestMapping(value = "/datasource", method = RequestMethod.GET)
	public Object getDataSource() {
		return dataSourceListConfig.getDataSource("medicclinic_demo");
	}
	
	
	
}

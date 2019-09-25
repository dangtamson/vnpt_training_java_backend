package com.data.mysql.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.mysql.config.DataConfig;
import com.data.mysql.config.DataSourceListConfig;
import com.data.mysql.model.ReturnResultSetModel;
import com.data.mysql.response.BaseResponse;

@RestController
@RequestMapping("api/v1.0/data")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DataApi {
	
	@Autowired
	private DataConfig dataConfig;
	
	@Autowired
	private DataSourceListConfig dataSourceListConfig;
	
	
	@RequestMapping(value = "get/{func}/{action}", method = RequestMethod.POST)
	public ResponseEntity<Object> get(
			@RequestHeader(value="appcode") String sAppCode
			,@PathVariable("func") String sFunc
			,@PathVariable("action") String sAction
			,@RequestBody(required=false) String sRequest) {
		
		final String MSG_DATASOURCE_MISING = "Datasource Notfound";

		try {
			
			// Lay datasource theo appcode
			 DriverManagerDataSource ds = dataSourceListConfig.getDataSource(sAppCode);
			 
			 if(ds==null) {
				 return new ResponseEntity<Object>(new BaseResponse("1", MSG_DATASOURCE_MISING, null), HttpStatus.OK);
			 }
			
			String sProcName = dataConfig.getFUNCTIONNAME_PREFIX() + sFunc + "_" + sAction; 
			
			// Tham so truyen
			MapSqlParameterSource _pars = new MapSqlParameterSource();
			_pars.addValue("i_uid", "");
			_pars.addValue("i_ip", "");
			String request = sRequest != null?sRequest:"{}";
			if (!request.equals("{}")) {
				JSONObject jsonObj=new JSONObject(request);
				JSONArray arKeys = jsonObj.names();
				
				for (int i = 0; i < arKeys.length (); ++i) {
					String key = arKeys.getString (i); 
					_pars.addValue(dataConfig.getINPUT_PREFIX() + arKeys.getString (i), jsonObj.getString (key).equals("null")?null:jsonObj.getString (key));
				}
			}
			
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(ds).withProcedureName(sProcName);
			Map<String, Object> _rs = jdbcCall.execute(_pars);
			Object oResponse = _rs.get("#result-set-1");	
			ds.getConnection().close();
			return new ResponseEntity<Object>(new BaseResponse("0", "Success" , oResponse), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new BaseResponse("1", e.getMessage(), null), HttpStatus.OK);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "set/{func}/{action}", method = RequestMethod.POST)
	public ResponseEntity<Object> set(
			@RequestHeader(value="appcode") String sAppCode
			,@PathVariable("func") String sFunc
			,@PathVariable("action") String sAction
			,@RequestBody String sRequest) {
		
		final String MSG_DATASOURCE_MISING = "Datasource Notfound";
		
		try {
			
			// Lay datasource theo appcode
			 DriverManagerDataSource ds = dataSourceListConfig.getDataSource(sAppCode);
			 
			 if(ds==null) {
				 return new ResponseEntity<Object>(new BaseResponse("1", MSG_DATASOURCE_MISING, null), HttpStatus.OK);
			 }
			
			String sProcName = dataConfig.getFUNCTIONNAME_PREFIX() + sFunc + "_" + sAction; 
			
			// Tham so truyen
			MapSqlParameterSource _pars = new MapSqlParameterSource();
			_pars.addValue("i_uid", "");
			_pars.addValue("i_ip", "");
			String request = sRequest != null?sRequest:"{}";
			if (!request.equals("{}")) {
				JSONObject jsonObj=new JSONObject(request);
				JSONArray arKeys = jsonObj.names();
				
				for (int i = 0; i < arKeys.length (); ++i) {
					String key = arKeys.getString (i); 
					_pars.addValue(dataConfig.getINPUT_PREFIX() + arKeys.getString (i), jsonObj.getString (key).equals("null")?null:jsonObj.getString (key));
				}
			}
			
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(ds).withProcedureName(sProcName);
			Map<String, Object> _rs = jdbcCall.execute(_pars);
			List<ReturnResultSetModel> listResult=new ArrayList<ReturnResultSetModel>();

			listResult = (List<ReturnResultSetModel>) _rs.get("#result-set-1");	
			ds.getConnection().close();
			return new ResponseEntity<Object>(listResult.get(0), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new BaseResponse("1", e.getMessage(), null), HttpStatus.OK);
		}
	}
}

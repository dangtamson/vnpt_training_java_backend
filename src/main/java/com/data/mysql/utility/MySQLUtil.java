package com.data.mysql.utility;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.data.mysql.config.DataConfig;
import com.data.mysql.response.BaseResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


//import oracle.jdbc.datasource.;

public class MySQLUtil {
	@Autowired
	private DataSource dataSource;
	//private JdbcTemplate jdbcTemplate;
	//private SimpleJdbcCall simpleJdbcCall;
	private static final Logger logger = LoggerFactory.getLogger(MySQLUtil.class);

	public MySQLUtil(DataConfig oConfig) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(oConfig.getDRIVER());
		ds.setUrl("jdbc:mysql://" + oConfig.getHOST() + ":" + oConfig.getPORT() +  "/" + oConfig.getDBNAME() +  "?useUnicode=yes&characterEncoding=UTF-8");
		ds.setUsername(oConfig.getUSERNAME());
		ds.setPassword(oConfig.getPASSWORD());
		this.dataSource = ds;
	}
	
	
	public BaseResponse callStoreProcedureBaseResponse(String pProcName, Map<String, Object> mParams) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName(pProcName);
		BaseResponse oRsp = new BaseResponse();
		try {
			Map<String, Object> _rs = jdbcCall.execute(mParams);
			String sJson = new ObjectMapper().writeValueAsString(_rs.get("#result-set-1"));
			List<BaseResponse> list = mapper.readValue(sJson, new
					 TypeReference<List<BaseResponse>>() { }); if (list.size() > 0) { oRsp =list.get(0); }
			return oRsp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	public Object callStoreProcedure(String pProcName, Map<String, Object> mParams) throws Exception {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName(pProcName);
			Map<String, Object> out = jdbcCall.execute(mParams);
			return out.get("#result-set-1");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	

	
	public Object callStoreProcedure(String pProcName) throws Exception {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName(pProcName);
			Map<String, Object> out = jdbcCall.execute();
			return out.get("#result-set-1");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	
	public String callStoreProcedure2Json(String pProcName,Map<String, Object> mParams) throws Exception {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName(pProcName);
			Map<String, Object> _rs = jdbcCall.execute(mParams);
			String _json = new ObjectMapper().writeValueAsString(_rs.get("#result-set-1"));
			return _json;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}

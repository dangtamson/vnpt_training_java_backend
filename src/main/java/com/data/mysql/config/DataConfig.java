package com.data.mysql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
	
	@Value("${db.driver}")
	private String DRIVER;
	@Value("${db.host}")
	private String HOST;
	@Value("${db.port}")
	private String PORT;
	@Value("${db.sid_servicename}")
	private String SID_SERVICENAME;
	@Value("${db.dbname}")
	private String DBNAME;
	@Value("${db.username}")
	private String USERNAME;
	@Value("${db.password}")
	private String PASSWORD;
	@Value("${db.functionname_prefix}")
	private String FUNCTIONNAME_PREFIX;
	@Value("${db.function_input_prefix}")
	private String INPUT_PREFIX;
	@Value("${db.function_output_prefix}")
	private String OUTPUT_PREFIX;
	@Value("${db.function_inoutput_prefix}")
	private String INOUTPUT_PREFIX;

	public String getDBNAME() {
		return DBNAME;
	}

	public void setDBNAME(String dBNAME) {
		DBNAME = dBNAME;
	}

	public String getDRIVER() {
		return DRIVER;
	}

	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}

	public String getFUNCTIONNAME_PREFIX() {
		return FUNCTIONNAME_PREFIX;
	}

	public void setFUNCTIONNAME_PREFIX(String fUNCTIONNAME_PREFIX) {
		FUNCTIONNAME_PREFIX = fUNCTIONNAME_PREFIX;
	}

	public String getINPUT_PREFIX() {
		return INPUT_PREFIX;
	}

	public void setINPUT_PREFIX(String iNPUT_PREFIX) {
		INPUT_PREFIX = iNPUT_PREFIX;
	}

	public String getOUTPUT_PREFIX() {
		return OUTPUT_PREFIX;
	}

	public void setOUTPUT_PREFIX(String oUTPUT_PREFIX) {
		OUTPUT_PREFIX = oUTPUT_PREFIX;
	}

	public String getINOUTPUT_PREFIX() {
		return INOUTPUT_PREFIX;
	}

	public void setINOUTPUT_PREFIX(String iNOUTPUT_PREFIX) {
		INOUTPUT_PREFIX = iNOUTPUT_PREFIX;
	}

	public String getHOST() {
		return HOST;
	}

	public void setHOST(String hOST) {
		HOST = hOST;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String pORT) {
		PORT = pORT;
	}

	public String getSID_SERVICENAME() {
		return SID_SERVICENAME;
	}

	public void setSID_SERVICENAME(String sID_SERVICENAME) {
		SID_SERVICENAME = sID_SERVICENAME;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	@Override
	public String toString() {
		return "DataConfig [HOST=" + HOST + ", PORT=" + PORT + ", SID_SERVICENAME=" + SID_SERVICENAME + ", USERNAME="
				+ USERNAME + ", PASSWORD=" + PASSWORD + "]";
	}

}

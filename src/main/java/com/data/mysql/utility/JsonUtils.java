package com.data.mysql.utility;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.mysql.response.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUtils {
	private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static final String EMPTY = "";

	private static ObjectMapper objectMapper() {
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objMapper;
	}

	public static String convertObjectToJson(Object object) {
		if (object == null) {
			return null;
		}
		try {
			return objectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.warn("Convert Object To Json", e);
			return EMPTY;
		}
	}

	@Deprecated
	public static String convertObjectToJson(Map<String, String> object) throws Exception {
		if (object == null) {
			return null;
		}
		try {
			return objectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.warn("Convert Object To Json", e);
			throw new Exception("DATA_FORMAT_EXCEPTION");
		}
	}

	public static <T> T convertJsonToObject(String str, Class<T> type) throws Exception {
		if (str == null)
			return null;
		try {
			if (type != null && type.equals(BaseResponse.class)) {
				throw new Exception("SESSION_EXPRIED");
			}
			return objectMapper().readValue(str, type);
		} catch (IOException e) {
			logger.warn("Convert Json To Object", e);
			throw new Exception("DATA_FORMAT_EXCEPTION");
		}

	}

	public static <T> T convertJsonToObject(String str, TypeReference<T> typeref) throws Exception {
		if (str == null)
			throw new Exception("DATA_FORMAT_EXCEPTION");
		try {
			if (typeref.getClass() != null && typeref.getClass().equals(BaseResponse.class)
					) {
				throw new Exception("SESSION_EXPRIED");
			}
			return objectMapper().readValue(str, typeref);
		} catch (IOException e) {
			logger.warn("Convert json to object", e);
			throw new Exception("DATA_FORMAT_EXCEPTION");
		}
	}

	public static String replateL2JSON(String str) {
		if (str == null)
			return "";
		return str.replace("'", "").replace("\\", "").replace("\"[", "[").replace("]\"", "]");
	}

}

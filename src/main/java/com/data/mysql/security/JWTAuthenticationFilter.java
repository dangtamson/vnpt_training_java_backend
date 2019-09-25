package com.data.mysql.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.data.mysql.response.BaseResponse;
import com.data.mysql.utility.JsonUtils;


public class JWTAuthenticationFilter extends GenericFilterBean {
	private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	private final String ENCODING = "utf-8";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			logger.warn("JWTAuthenticationFilter doFilter throw exception", e);
			response.setCharacterEncoding(ENCODING);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(JsonUtils.convertObjectToJson(new BaseResponse("401","Unauthorized",e.getMessage())));
			response.getWriter().close();
			return;
		} 

		filterChain.doFilter(request, response);

	}
}
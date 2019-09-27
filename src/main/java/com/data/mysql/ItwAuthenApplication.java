package com.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.data.mysql.config.AppConfig;



@SpringBootApplication
public class ItwAuthenApplication extends SpringBootServletInitializer implements ApplicationRunner {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppConfig config;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ItwAuthenApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ItwAuthenApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		logger.error("#######################################################");
		logger.error("# AppName: "+ config.getAppName() +" STARTED!");
		logger.error("# Code: "+ config.getCode() );
		logger.error("# Onwer: "+ config.getOwner());
		logger.error("#######################################################");
	}

}

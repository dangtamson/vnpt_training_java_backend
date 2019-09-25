package com.data.mysql.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.data.mysql.model.DataSourceModel;

@Component
@ConfigurationProperties("multitenancy.database")
public class DataSourceListConfig {
	private List<DataSourceModel> dataSourceList;

	public List<DataSourceModel> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<DataSourceModel> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}
	
    public DriverManagerDataSource getDataSource(String appCode)
    {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	DataSourceModel dts = null;
    	for (DataSourceModel dataSourceItem : dataSourceList) {
			if (dataSourceItem.getAppcode().equals(appCode)) {
				dts = dataSourceItem;
			}
		}
    	
    	if(dts!=null) {
    		dataSource.setDriverClassName(dts.getDriverclassname());
            dataSource.setUrl(dts.getUrl());
            dataSource.setUsername(dts.getUsername());
            dataSource.setPassword(dts.getPassword());
            dataSource.setSchema(dts.getSchema());
            return dataSource;
    	}else {
    		return null;
    	}
    }
	
}

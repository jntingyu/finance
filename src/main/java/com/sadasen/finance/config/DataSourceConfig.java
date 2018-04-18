package com.sadasen.finance.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.sadasen.finance.util.Utils;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Configuration
public class DataSourceConfig {
	
	@Bean(name="dataSource")
	public DataSource dataSource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(Utils.dbDecrypt(env.getProperty("spring.datasource.password")));
		ds.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		return ds;
	}

}

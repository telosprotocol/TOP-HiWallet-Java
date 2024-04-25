//package com.common.core;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//import com.gitee.magic.core.exception.ApplicationException;
//
///**
// * 数据源配置,配置start.framework.print.sqltablescript=true生成数据表可打开以下代码进行数据表生成
// * @author start
// *
// */
//@Configuration
//public class DataSourceConfiguration {
//	
//	@Bean
//	@Primary
//	@ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master")
//	public DataSource datasource() {
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		// 最小连接池数量
//		dataSource.setMinIdle(200);
//		// 最大连接池数量
//		dataSource.setMaxActive(200);
//		// 获取连接时最大等待时间
//		dataSource.setMaxWait(3000);
//		dataSource.setTimeBetweenEvictionRunsMillis(60000);
//		try {
//			dataSource.setFilters("stat,wall");
//		} catch (SQLException e) {
//			throw new ApplicationException(e);
//		}
//		return dataSource;
//	}
//	
//}

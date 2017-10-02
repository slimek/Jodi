package com.slimek.jodi;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JodiApplication implements ApplicationListener<ContextRefreshedEvent> {

	private static boolean started = false;
	private static ComboPooledDataSource cpds = null;
	
	@Override
	public synchronized void onApplicationEvent(ContextRefreshedEvent event) {
		
		Logger logger = LoggerFactory.getLogger(JodiApplication.class);
		logger.info("Application ContextRefreshedEvent triggered");
		
		if (!started) {
			started = true;
				
			try {
				// 請先在 src/main/resources 目錄新增 secret.properties 設定檔
				Properties secret = new Properties();
				InputStream stream = getClass().getClassLoader().getResourceAsStream("secret.properties");
				secret.load(stream);

				String jdbcUrl = String.format("jdbc:mysql://%s/global", secret.getProperty("db.host"));
				
				cpds = new ComboPooledDataSource();
				cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
				cpds.setJdbcUrl(jdbcUrl);
				cpds.setUser(secret.getProperty("db.user"));
				cpds.setPassword(secret.getProperty("db.password"));
				
				// 為測試拿不到連線的情況，故意將最大值降低
				cpds.setMinPoolSize(1);
				cpds.setInitialPoolSize(1);
				cpds.setMaxPoolSize(2);
				
				cpds.setCheckoutTimeout(5000);
				
				logger.info("Max pool size: " + cpds.getMaxPoolSize());
				logger.info("Checkout timeout: " + cpds.getCheckoutTimeout());
				
			} catch (Exception ex) {
				logger.error("Connect database failed: " + ex.getMessage());
			}
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return cpds.getConnection();
	}
	
	public static String getStatus() throws SQLException {
		 int curr = cpds.getNumConnections();
		 int idle = cpds.getNumIdleConnections();
		 int busy = cpds.getNumBusyConnections();
		 return String.format("%d %d %d", curr, idle, busy);
	}
}

package com.rubypaper.jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rubypaper.jdbc.util.JDBCConnectionManager;

@Configuration
@EnableConfigurationProperties(JDBCConnectionManagerProperties.class) //@ConfigurationProperties를 붙인 클래스를 Spring이 인식하고 Bean으로 만들어 주입하기 위해 사용
public class BoardAutoConfiguration {
	 
	@Autowired
	 private JDBCConnectionManagerProperties properties;
	 
	@Bean
	@ConditionalOnMissingBean //조건에 따라 빈등록을 처리 -> 사용자가 정의한 빈객체가 없는 경우에만 자동설정에 의한 빈객체가 등록되도록 설정
	JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass(properties.getDriverClass());
		manager.setUrl(properties.getUrl());
		manager.setUsername(properties.getUsername());
		manager.setPassword(properties.getPassword());
		return manager;
	}
}

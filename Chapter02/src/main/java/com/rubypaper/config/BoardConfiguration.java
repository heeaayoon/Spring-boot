package com.rubypaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

//@Configuration
public class BoardConfiguration {
	//@Bean //A bean with that name has already been defined in class path resource [com/rubypaper/config/BoardConfiguration.class] and overriding is disabled.
		  //JDBCConnectionManager 타입의 객체를 다시 만들어서 등록하려하면, 이미 등록된 같은 타입의 객체와 충동이 발생함
	JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:tcp://localhost/~/test");
		manager.setUsername("sa");
		manager.setPassword("");
		return manager;
	}
}
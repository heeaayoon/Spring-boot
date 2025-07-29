package com.rubypaper.jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="board.jdbc") // application.properties 파일에서 board.jdbc로 시작하는 항목들만 골라 자바 클래스(객체)의 필드에 자동으로 바인딩(연결)해주는
public class JDBCConnectionManagerProperties {
	private String driverClass;
	 private String url;
	 private String username;
	 private String password; 
	
	 public String getDriverClass() {
		 return driverClass;
	 }
	 
	 public void setDriverClass(String driverClass) {
		 this.driverClass = driverClass;
	 }
	 
	 public String getUrl() {
		 return url;
	 }
	 
	 public void setUrl(String url) {
		 this.url = url;
	 }
	 
	 public String getUsername() {
		 return username;
	 }
	 public void setUsername(String username) {
		 this.username = username;
	 }
	 public String getPassword() {
		 return password;
	 }
	 public void setPassword(String password) {
		 this.password = password;
	 }
}

package com.rubypaper.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security -> 
						security
						.requestMatchers("/member/**").authenticated()
						.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().permitAll()
				);
		
//		http.formLogin(form->{}); //기본 로그인 화면 이용
		//만든 로그인 화면 이용
		http.formLogin(form->
				form
				.loginPage("/login") //로그인에 사용할 URL
				.defaultSuccessUrl("/loginSuccess",true) //로그인 성공 시 이동할 URL
				);
		
		//접근권한 없음 설정
		http.exceptionHandling(ex->
				ex
				.accessDeniedPage("/accessDenied")
				);
		
		//로그아웃 설정
		http.logout(logout->
				logout
				.invalidateHttpSession(true) //현재 브라우저와 연결된 세션 강제 종료
				.deleteCookies("JSESSIONID") //세션 아이디가 저장된 쿠키 삭제
				.logoutSuccessUrl("/login") //로그아웃 후 이동할 URL
				);
		
		http.csrf(cf->cf.disable());
		return http.build();
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()
//			.withUser("member").password("{noop}abcd").roles("MEMBER");
//		auth.inMemoryAuthentication()
//		.withUser("manager").password("{noop}abcd").roles("MANAGER");
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("{noop}abcd").roles("ADMIN");
//		
//		String query1 = "select id username, concat('{noop}' , password) password, true enabled from member where id = ?";
//		String query2 = "select id, role from member where id = ?";
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(query1)
//			.authoritiesByUsernameQuery(query2);
//	}
	
//	@Autowired
//	private DataSource dataSource;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	
}

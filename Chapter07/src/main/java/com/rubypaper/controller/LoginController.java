package com.rubypaper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubypaper.config.SecurityUser;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth1")
	public @ResponseBody ResponseEntity<?> auth1(@AuthenticationPrincipal SecurityUser user){
		if(user==null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/auth")
	public @ResponseBody void auth2(Authentication auth) {
		System.out.println(auth.getPrincipal());
	}
}

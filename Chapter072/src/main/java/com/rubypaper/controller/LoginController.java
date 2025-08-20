package com.rubypaper.controller;
import com.rubypaper.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubypaper.domain.Member;

@Controller
public class LoginController {

    private final MemberService memberService;

    LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinProc(Member m) {
		memberService.save(m);
		return "welcome";
	}
	
}

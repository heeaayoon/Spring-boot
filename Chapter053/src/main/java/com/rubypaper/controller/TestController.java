package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@RestController
public class TestController {

	//member 데이터를 가져와서 테스트
	@Autowired
	private MemberRepository memberRepo; 
	
	@GetMapping("/member")
	public List<Member> getMembers(){
		return memberRepo.findAll();
	}
}

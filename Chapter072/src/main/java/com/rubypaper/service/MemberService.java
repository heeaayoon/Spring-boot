package com.rubypaper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;


@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Member m) {
		m.setPass(encoder.encode(m.getPass()));
		m.setRole(Role.ROLE_MEMBER);
		m.setEnabled(true);
		memberRepo.save(m);
	}
}

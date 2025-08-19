package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class PasswordEncoderTest {

	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		{
		Member member = new Member();
		member.setId("member");
		member.setPassword(encoder.encode("member123"));
		member.setName("멤버");
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		memberRepo.save(member);
		}
		{
		Member member = new Member();
		member.setId("manager");
		member.setPassword(encoder.encode("manager123"));
		member.setName("매니저");
		member.setRole(Role.ROLE_MANAGER);
		member.setEnabled(true);
		memberRepo.save(member);
		}
		{
		Member member = new Member();
		member.setId("manager2");
		member.setPassword(encoder.encode("manager456"));
		member.setName("매니저2");
		member.setRole(Role.ROLE_MANAGER);
		member.setEnabled(true);
		memberRepo.save(member);
		}
		{
		Member member = new Member();
		member.setId("admin");
		member.setPassword(encoder.encode("admin123"));
		member.setName("관리자");
		member.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);
		memberRepo.save(member);
		}
	}
}

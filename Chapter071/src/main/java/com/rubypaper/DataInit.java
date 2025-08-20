package com.rubypaper;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistance.MemberRepository;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor //final이 붙은 필드를 파라미터로 하는 생성자를 자동으로 추가
public class DataInit implements ApplicationRunner {

	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepo.save(Member.builder()
								.id("member")
								.name("홍길동")
								.pass(encoder.encode("1234"))
								.role(Role.ROLE_MEMBER)
								.enabled(true)
								.build());
		
		memberRepo.save(Member.builder()
								.id("manager")
								.name("홍이동")
								.pass(encoder.encode("1234"))
								.role(Role.ROLE_MANAGER)
								.enabled(true)
								.build());
		
		memberRepo.save(Member.builder()
								.id("admin")
								.name("홍삼동")
								.pass(encoder.encode("1234"))
								.role(Role.ROLE_ADMIN)
								.enabled(true)
								.build());
	}
	
	
}

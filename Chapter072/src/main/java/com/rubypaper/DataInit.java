package com.rubypaper;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
	
	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepo.save(Member.builder()
				.id("member")
				.name("홍1")
				.pass(encoder.encode("1234"))
				.role(Role.ROLE_MEMBER)
				.enabled(true)
				.build()); //Member 객체를 생성해서 데이터베이스에 저장함
		
		memberRepo.save(Member.builder()
				.id("manager")
				.name("홍2")
				.pass(encoder.encode("1234"))
				.role(Role.ROLE_MANAGER)
				.enabled(true)
				.build()); //Member 객체를 생성해서 데이터베이스에 저장함
		
		memberRepo.save(Member.builder()
				.id("admin")
				.name("홍3")
				.pass(encoder.encode("1234"))
				.role(Role.ROLE_ADMIN)
				.enabled(true)
				.build()); //Member 객체를 생성해서 데이터베이스에 저장함
	}
}

package com.rubypaper.service;
//Spring Security가 사용자를 인증할 때 이 클래스를 사용

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService{

	private final MemberRepository memberRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member m = memberRepo.findById(username) //findById : 사용자가 입력한 아이디(username)를 가지고 MemberRepository를 통해 데이터베이스에 있는 Member 정보를 찾기 -> Optional<Member> 타입으로 결과를 반환
							.orElseThrow(()->new UsernameNotFoundException("Not Found"+username)); //Optional 객체에 정보가 없을 때,
		
		//Optional 객체에 정보가 있을 때, DB에서 가져온 Member 객체(m)의 정보를 UserDetails 타입으로 반환 => User 이용하기
		return new User(m.getId(), m.getPass(), 
				AuthorityUtils.createAuthorityList(m.getRole().toString()));  //m.getRole()로 가져온 역할을 Spring Security가 인식할 수 있는 사용자의 권한목록으로 변환
	}
}

package edu.pnu.controller;

import edu.pnu.domain.MemberDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api") //공통 url //localhost:8080/api/...
public class MemberController {
	
	//데이터 저장용 객체 생성 - 10개
	private List<MemberDTO> list = new ArrayList<>();
	public MemberController() {
		for (int i=1;i<=10;i++) {
			list.add(MemberDTO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date()).build());
		}
	}
	
	//@GetMapping에서 {} : 경로 변수
	//@RequestBody : 본문에 들어 있는 내용(body>raw>{객체})을 해당 데이터 타입으로 변환해서 넘기기
	
	//검색(read - select All)
	@GetMapping("/member")
	public List<MemberDTO> getAllMember(){
		return list;
	}
	
	//검색(read - select One)
	@GetMapping("/member/{id}")
	public MemberDTO getMemberById(@PathVariable Integer id){
		for(MemberDTO m:list) {
			if(m.getId()==id)
				return m;
		}
		return null;
	}
	
	//삭제(Delete - delete)
	@DeleteMapping("/member/{id}")
	public void deleteMember(@PathVariable Integer id){
		for(MemberDTO m:list) {
			if(m.getId()==id)
				list.remove(m);
		}
	}
	
	//입력(Create - insert)
	@PostMapping("/member")
	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		list.add(memberDTO);
		return memberDTO;
	}
	
	//수정(update - 객체 교체)
	@PutMapping("/member/{id}")
	public MemberDTO putmember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
//		MemberDTO member = getMemberById(id);
//		list.remove(member); //id에 해당하는 객체를 지우고
//		list.add(memberDTO); //맨 마지막에 새로운 객체를 추가하기
		list.set(id-1, memberDTO); //set(index, 새로운 객체) : 해당 인덱스의 객체를 지우고, 그 자리에 새로운 객체로 교체
        return memberDTO;
	}
	
	//수정(update - 일부 정보만 교체)
	@PatchMapping("/member/{id}")
	public MemberDTO patchmember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		//수정할 객체 찾기
		MemberDTO oldMember = null;
		for(MemberDTO m:list) {
			if(m.getId()==id)
				oldMember = m;
		}
		
		//찾은 객체를 수정하기
		//id, pass, name, regidate 중에 입력한 것만 수정하기
		if(memberDTO.getId()!=null) {
			oldMember.setId(memberDTO.getId());
		}
		if(memberDTO.getPass()!=null) {
			oldMember.setPass(memberDTO.getPass());
		}
		if(memberDTO.getName()!=null) {
			oldMember.setName(memberDTO.getName());
		}
		if(memberDTO.getRegidate()!=null) {
			oldMember.setRegidate(memberDTO.getRegidate());
		}	
		return oldMember;
	}
}

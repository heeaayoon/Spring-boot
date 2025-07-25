package edu.pnu.controller;

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

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api") //공통 url //localhost:8080/api/...
public class MemberController {

	private MemberService memberService;
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	//전체 검색(read - select All)
	@GetMapping("/member")
	public List<MemberDTO> getAllMember(){
		return memberService.getAllMember();
	}
	
//	//일부만 검색(read - select One)
//	@GetMapping("/member/{id}")
//	public MemberDTO getMemberById(@PathVariable Integer id){
//		return memberService.getMemberById(id);
//	}
//		
//	//입력(Create - insert)
//	@PostMapping("/member")
//	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
//		return memberService.postMember(memberDTO);
//		
//	}
//
//	//삭제(Delete - delete)
//	@DeleteMapping("/member/{id}")
//	public void deleteMember(@PathVariable Integer id){
//		memberService.deleteMember(id);
//	}
//	
//	//전체 수정(put - 객체 교체)
//	@PutMapping("/member/{id}")
//	public MemberDTO putmember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
//		return memberService.putmember(id,memberDTO);		
//	}
//	
//	//일부만 수정(patch - 일부 정보만 교체)
//	@PatchMapping("/member/{id}")
//	public MemberDTO patchmember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
//		return memberService.patchmember(id,memberDTO);
//	}
}

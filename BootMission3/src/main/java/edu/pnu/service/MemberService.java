package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	//전체 검색(read - select All)
	public List<MemberDTO> getAllMember() {
		return memberDao.getAllMember();
	}

	//일부만 검색(read - select One)
	public MemberDTO getMemberById(Integer id) {
		return memberDao.getMemberById(id);
	}
	
	//입력(Create - insert)
	public MemberDTO postMember(MemberDTO memberDTO) {
		return memberDao.postMember(memberDTO);
	}

	//삭제(Delete - delete)
	public void deleteMember(Integer id) {
		memberDao.deleteMember(id);
	}
	
	//전체 수정(put - 객체 교체)
	public MemberDTO putmember(Integer id, MemberDTO memberDTO) {
		return memberDao.putmember(id, memberDTO);
	}

	//일부만 수정(patch - 일부 정보만 교체)
	public MemberDTO patchmember(Integer id, MemberDTO memberDTO) {
		return memberDao.patchmember(id, memberDTO); //수정할 객체를 찾지 못하면 null 반환 
	}	
}

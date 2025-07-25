package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//전체 검색(read - select All)
	public List<MemberDTO> getAllMember() {
		return memberDao.getAllMember();
	}
	
//	//일부만 검색(read - select One)
//	public MemberDTO getMemberById(Integer id) {
//		for(MemberDTO m : list) {
//			if(m.getId().equals(id))
//				return m;
//		}
//		return null;
//	}
//	
//	//입력(Create - insert)
//	public MemberDTO postMember(MemberDTO memberDTO) {
//		list.add(memberDTO);
//		return memberDTO;
//	}
//
//	//삭제(Delete - delete)
//	public List<MemberDTO> deleteMember(Integer id) {
//		for(MemberDTO m : list) {
//			if(m.getId()==id) {
//				list.remove(m);
//				return list;
//			}
//		}
//		return null;
//	}
//	
//	//전체 수정(put - 객체 교체)
//	public MemberDTO putmember(Integer id, MemberDTO memberDTO) {
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i).getId()==id) {
//				memberDTO.setId(id); //기존의 id를 가져다 씀
//				memberDTO.setRegidate(list.get(i).getRegidate()); //기존의 regidate 를 가져다 씀
//				list.set(i, memberDTO); //set(index, 새로운 객체) : 해당 인덱스의 객체를 지우고, 그 자리에 새로운 객체로 교체
//				return memberDTO;
//			}
//		}	
//		return null; //수정할 객체를 찾지 못하면 null 반환 
//	}
//
//	//일부만 수정(patch - 일부 정보만 교체)
//	public MemberDTO patchmember(Integer id, MemberDTO memberDTO) {
//		//수정할 객체 찾기
//		MemberDTO oldMember = null;
//		for(MemberDTO m:list) {
//			if(m.getId()==id) {
//				oldMember = m;
//				break;
//			}
//		}
//		//찾은 객체를 수정하기
//		//pass, name 중에 입력한 것만 수정하기
//		//id와 regidate는 수정하지 X
//		if(oldMember!=null) {
//			if(memberDTO.getPass()!=null) {
//				oldMember.setPass(memberDTO.getPass());
//			}
//			if(memberDTO.getName()!=null) {
//				oldMember.setName(memberDTO.getName());
//			}
//			return oldMember;			
//		}
//		return null; //수정할 객체를 찾지 못하면 null 반환 
//	}	
}

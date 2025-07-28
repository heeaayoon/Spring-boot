package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

@SuppressWarnings("unchecked")
public class MemberService {
	private MemberDao memberDao;
	private LogDao logDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
		this.logDao = new LogDao();
	}
	
	//전체 검색(read - select All)
	public List<MemberDTO> getAllMember() {
		Map<String, Object> map =  memberDao.getAllMember();
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
		return (List<MemberDTO>)map.get("result"); //map 객체에 "result"라는 key에 해당하는 값을 가져오기(mission3의 list값에 해당함)
	}

	//일부만 검색(read - select One)
	public MemberDTO getMemberById(Integer id) {
		Map<String, Object> map =  memberDao.getMemberById(id);
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
		return (MemberDTO)map.get("result");
	}
	
	//입력(Create - insert)
	public MemberDTO postMember(MemberDTO memberDTO) {
		Map<String, Object> map =  memberDao.postMember(memberDTO);
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
		return (MemberDTO)map.get("result");
	}

	//삭제(Delete - delete)
	public void deleteMember(Integer id) {
		Map<String, Object> map =  memberDao.deleteMember(id);
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
	}
	
	//전체 수정(put - 객체 교체)
	public MemberDTO putmember(Integer id, MemberDTO memberDTO) {
		Map<String, Object> map =  memberDao.putmember(id, memberDTO);
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
		System.out.println();
		return (MemberDTO)map.get("result");
	}

	//일부만 수정(patch - 일부 정보만 교체)
	public MemberDTO patchmember(Integer id, MemberDTO memberDTO) {
		Map<String, Object> map =  memberDao.patchmember(id, memberDTO);
		logDao.addLog(map); //logDao 객체의 addLog 메서드 사용
		return (MemberDTO)map.get("result");
	}	
}

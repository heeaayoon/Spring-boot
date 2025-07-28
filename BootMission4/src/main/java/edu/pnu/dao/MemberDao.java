package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDao {
	private Connection con; 
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result; //성공여부 확인
	

	//기본 생성자
	public MemberDao() {
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//DB 연결
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url,id,pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
	}
	
	//전체 검색(read - select All)
	public Map<String, Object> getAllMember() {
		List<MemberDTO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		//쿼리문
		String query = "select id, pass, name, regidate from member";
		map.put("method", "get"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		map.put("sqlstring", query); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) { //dto에 값 저장
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRegidate(rs.getDate("regidate"));
				list.add(dto);
			}
			map.put("success", true); //map 객체에 "success"라는 key에 해당하는 값을 추가
		}catch(Exception e){
			System.out.println("모든 정보를 가져오는 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}
		map.put("result", list); //map 객체에 "result"라는 key에 해당하는 값을 추가
		return map; //map에 저장된 내용을 전부 반환
	}

	//일부만 검색(read - select One)
	public Map<String, Object> getMemberById(Integer id) {
		MemberDTO dto = null; //검색할 id가 존재하는지 확인하기 위해 처음에는 null로 초기화 -> 데이터가 없는 상태임
		Map<String, Object> map = new HashMap<>();
		
		//쿼리문
		String query = "select id, pass, name, regidate from member where id = ?";
		map.put("method", "get"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id); 
			//System.out.println(psmt.toString());
			map.put("sqlstring", psmt.toString().split(":")[1].trim()); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
			rs = psmt.executeQuery();
			
			if(rs.next()) { //결과가 존재할 경우에만, 객체를 생성
				System.out.println("해당 id가 존재합니다.");
				dto = new MemberDTO(); //여기서 객체를 생성
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRegidate(rs.getDate("regidate"));
				map.put("success", true); //map 객체에 "success"라는 key에 해당하는 값을 추가
			}else {
				map.put("success", false); //map 객체에 "success"라는 key에 해당하는 값을 추가
			}
		}catch(Exception e){
			System.out.println("id에 해당하는 정보를 가져오는 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}		
		map.put("result", dto); //map 객체에 "result"라는 key에 해당하는 값을 추가
		return map; //map에 저장된 내용을 전부 반환
	}

	//입력(Create - insert)
	//id와 regidate는 자동입력되도록 mysql에서 설정
	public Map<String, Object> postMember(MemberDTO memberDTO) {
		Map<String, Object> map = new HashMap<>();
		//쿼리문
		String query = "insert into member (pass,name) values (?,?)";
		map.put("method", "post"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			map.put("sqlstring", psmt.toString().split(":")[1].trim()); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
			
			result = psmt.executeUpdate();
			map.put("success", true); //map 객체에 "success"라는 key에 해당하는 값을 추가
		}catch(Exception e){
			System.out.println("입력 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}		
		if(result==1) { //insert에 성공하면
			map.put("result", memberDTO); //map 객체에 "result"라는 key에 해당하는 값을 추가
			return map; //map에 저장된 내용을 전부 반환
		} else //insert에 실패하면
			return null;
	}

	//삭제(Delete - delete)
	public Map<String, Object> deleteMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		// 수정할 id가 존재하는지 확인
        Map<String, Object> existingMember = getMemberById(id);
        //System.out.println(existingMember);
        if (existingMember.get("result") == null) {
            //System.out.println("ID " + id + "에 해당하는 값이 존재하지 않습니다.");
			map.put("success", false);
        }
        
		//쿼리문
		String query = "delete from member where id = ?";
		map.put("method", "delete"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			map.put("sqlstring", psmt.toString().split(":")[1].trim()); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
			result = psmt.executeUpdate();
			//System.out.println(result);
			if(result==1) { //update 가 제대로 되었을 경우에만(해당 id를 가지는 데이터가 있는 경우에만)
				map.put("success", true);				
			}else {
				map.put("success", false);
				map.put("sqlstring","ID " + id + "에 해당하는 값이 존재하지 않습니다.");
			}
		}catch(Exception e){
			System.out.println("삭제 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}
		//System.out.println(map);
		return map; //map에 저장된 내용을 전부 반환
	}

	//전체 수정(put - 객체 교체)
	public Map<String, Object> putmember(Integer id, MemberDTO memberDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("method", "put"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		
		// 수정할 id가 존재하는지 확인
        Map<String, Object> existingMember = getMemberById(id);
        System.out.println(existingMember);
        if (existingMember.get("result") == null) {
        	map.put("sqlstring","ID " + id + "에 해당하는 값이 존재하지 않습니다.");
			map.put("success", false);
			map.put("result", null);
			return map;
        }
        
		//쿼리문
		String query = "update member set pass = ?, name =? where id = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);
			map.put("sqlstring", psmt.toString().split(":")[1].trim()); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
			result = psmt.executeUpdate();
			if(result==1) { //update 가 제대로 되었을 경우에만(해당 id를 가지는 데이터가 있는 경우에만)
				map.put("success", true);				
			}else {
				map.put("success", false);
			}
		}catch(Exception e){
			System.out.println("전체 수정 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}
		if(result==1) 	map.put("result", memberDTO); //map 객체에 "result"라는 key에 해당하는 값을 추가
		else 			map.put("result", null); //map 객체에 "result"라는 key에 해당하는 값을 추가
		return map;
	}
	
	//일부만 수정(patch - 일부 정보만 교체)
	//조회 후 수정하기
	public Map<String, Object> patchmember(Integer id, MemberDTO memberDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("method", "patch"); //map 객체에 "method"이라는 key에 해당하는 값을 추가
		
		// 수정할 id가 존재하는지 확인
        Map<String, Object> existingMember = getMemberById(id);
        System.out.println(existingMember);
        if (existingMember.get("result") == null) {
        	map.put("sqlstring","ID " + id + "에 해당하는 값이 존재하지 않습니다.");
			map.put("success", false);
			map.put("result", null);
			return map;
        }
		
        //들어온 값이 있는 경우에만 기존 객체 업데이트
        if (memberDTO.getPass()!=null) existingMember.setPass(memberDTO.getPass());
        if (memberDTO.getName()!=null) existingMember.setName(memberDTO.getName());
        
		//수정된 객체와 쿼리문을 이용해서 DB업데이트
		String query = "update member set pass = ?, name =? where id = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, existingMember.getPass());
			psmt.setString(2, existingMember.getName());
			psmt.setInt(3, existingMember.getId());
			map.put("sqlstring", psmt.toString().split(":")[1].trim()); //map 객체에 "sqlstring"이라는 key에 해당하는 값을 추가
			result = psmt.executeUpdate();
			map.put("success", true);
		}catch(Exception e){
			System.out.println("일부 수정 중 예외 발생");
			e.printStackTrace();
			map.put("success", false);
		}	
		if(result==1) { //patch에 성공하면
			map.put("result", existingMember); //map 객체에 "result"라는 key에 해당하는 값을 추가
			return map; //map에 저장된 내용을 전부 반환
		} else //put에 실패하면
			return null;
	}
}

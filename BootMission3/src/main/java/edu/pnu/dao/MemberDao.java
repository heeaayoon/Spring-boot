package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<MemberDTO> getAllMember() {
		List<MemberDTO> list = new ArrayList<>();
		//쿼리문
		String query = "select id, pass, name, regidate from member";
		//System.out.println(query);
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
		}catch(Exception e){
			System.out.println("모든 정보를 가져오는 중 예외 발생");
			e.printStackTrace();
		}		
		return list; //저장된 내용을 전부 반환
	}

	//일부만 검색(read - select One)
	public MemberDTO getMemberById(Integer id) {
		
		MemberDTO list = new MemberDTO();
		//쿼리문
		String query = "select id, pass, name, regidate from member where id = ?";
		//System.out.println(query);
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id); 
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				list.setId(rs.getInt(1));
				list.setName(rs.getString("name"));
				list.setPass(rs.getString("pass"));
				list.setRegidate(rs.getDate("regidate"));
			}
		}catch(Exception e){
			System.out.println("id에 해당하는 정보를 가져오는 중 예외 발생");
			e.printStackTrace();
		}		
		return list; //저장된 내용을 전부 반환
	}

	//입력(Create - insert)
	//id와 regidate는 자동입력되도록 mysql에서 설정
	public MemberDTO postMember(MemberDTO memberDTO) {
		//쿼리문
		String query = "insert into member (pass,name) values (?,?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			
			result = psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("입력 중 예외 발생");
			e.printStackTrace();
		}		
		if(result==1) //insert에 성공하면
			return memberDTO;
		else
			return null;
	}

	//삭제(Delete - delete)
	public void deleteMember(Integer id) {
		// 수정할 id가 존재하는지 확인
        MemberDTO existingMember = getMemberById(id);
        if (existingMember == null) {
            System.out.println("ID " + id + "에 해당하는 값이 존재하지 않습니다.");
        }
		//쿼리문
		String query = "delete from member where id = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			result = psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("삭제 중 예외 발생");
			e.printStackTrace();
		}		
	}
	
	//전체 수정(put - 객체 교체)
	public MemberDTO putmember(Integer id, MemberDTO memberDTO) {
		// 수정할 id가 존재하는지 확인
        MemberDTO existingMember = getMemberById(id);
        if (existingMember == null) {
            System.out.println("ID " + id + "에 해당하는 값이 존재하지 않습니다.");
            return null; // 대상이 없으면 null 반환
        }
		//쿼리문
		String query = "update member set pass = ?, name =? where id = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberDTO.getPass());
			psmt.setString(2, memberDTO.getName());
			psmt.setInt(3, id);
			result = psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("전체 수정 중 예외 발생");
			e.printStackTrace();
		}	
		if(result==1) //put 에 성공하면
			return memberDTO;
		else
			return null;
	}
	
	//일부만 수정(patch - 일부 정보만 교체)
	//조회 후 수정하기
	public MemberDTO patchmember(Integer id, MemberDTO memberDTO) {
		// 수정할 id가 존재하는지 확인
        MemberDTO existingMember = getMemberById(id);
        if (existingMember == null) {
            System.out.println("ID " + id + "에 해당하는 값이 존재하지 않습니다.");
            return null; // 대상이 없으면 null 반환
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
			result = psmt.executeUpdate();
		}catch(Exception e){
			System.out.println("일부 수정 중 예외 발생");
			e.printStackTrace();
		}	
		if(result==1) //put 에 성공하면
			return existingMember;
		else
			return null;
	}
}

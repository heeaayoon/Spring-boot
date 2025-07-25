package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;

@Repository
public class MemberDao {
	private Connection con; 

	//기본 생성자
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// application.properties에 설정한 정보로 DB 연결 시도
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
	
	List<MemberDTO> list = new ArrayList<>();
    PreparedStatement psmt = null;
    ResultSet rs = null;

	public List<MemberDTO> getAllMember() {
		//쿼리문
		String query = "select id, pass, name, regidate from member";
		System.out.println(query);
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			if(rs.next()) { //dto에 값 저장
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

	public MemberDTO getMemberById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class LogDao {
	private Connection con; 
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result; //성공여부 확인
	

	//기본 생성자
	public LogDao(){
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
	
	//Log를 남기는 메서드
	public void addLog(Map<String, Object> map) {
		//쿼리문 
		String query = "insert into dblog (method, sqlstring, success) values (?,?,?)";
		String method = null;
		String sqlstring = null;
		boolean success = false;
		if(map!=null) {
			method = (String) map.get("method");
			sqlstring = (String) map.get("sqlstring");
			success = (boolean) map.get("success");			
		}
		//System.out.println(success);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, method);
			psmt.setString(2, sqlstring);
			psmt.setBoolean(3, success); //1이 입력됨
			
			result = psmt.executeUpdate();
			System.out.println("로그입력 성공");
		}catch(Exception e){
			System.out.println("로그 입력 중 예외 발생");
			e.printStackTrace();
		}		
	}
	
}

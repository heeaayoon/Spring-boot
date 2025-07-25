package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // Getter생성
@Setter // Setter생성
@ToString // toString()메소드생성
@AllArgsConstructor // 모든 멤버변수를 포함하는 생성자 생성
@NoArgsConstructor //기본생성자를 생성
@Builder 
public class MemberDTO {
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;
}
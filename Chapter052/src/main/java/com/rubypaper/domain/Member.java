package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	//양방향 연관 관계
	//mappedBy : foreign key를 가지지 않는 쪽을 명시(Board.java의 private Member "member"를 의미함) --> board는 외래키를 가진(member_id) 테이블/member은 아님
//	@ToString.Exclude //StackOverflowError 제거하기 위한 방법 2
//	@JsonIgnore //@JsonIgnore을 사용하지 않으면, member 데이터를 가져올 때는 전부 다(board 데이터까지) 가져옴
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private List<Board> boardList = new ArrayList<>();
}
package com.rubypaper.domain;

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
@Builder // 객체생성시 Builder Pattern 제공 -> Board 객체정의시 Lombok의 Builder Pattern 사용 -> BoardController.java 확인하기
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default
	private Date createDate = new Date();
	private int cnt;
}
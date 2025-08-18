package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity //Entity 클래스로 변환 //클래스 이름과 동일한 테이블과 매핑됨
public class Board {
	@Id //테이블의 기본키 매핑
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@Id가 선언된 필드에 기본 키 값을 자동으로 할당
	private Long seq;
	private String title;
	@Column(updatable = false)
	private String writer;
	private String content;
	@Builder.Default
	@Temporal(TemporalType.DATE)
	@Column(insertable = false, updatable = false, columnDefinition = "date default (curdate())") 
	//@column의 insertable 속성 : insert를 생성할 때 이 컬럼을 포함할지 여부(기본 true)
	//@column의 updatable 속성 : update를 생성할 때 이 컬럼을 포함할지 여부(기본 true)
	//@column의 columnDefinition 속성 : 이 컬럼의 DDL문을 직접 작성
	private Date createDate = new Date();
	@Builder.Default
	@Column(insertable = false, updatable = true, columnDefinition = "bigint default 0")
	private Long cnt = 0L;
}

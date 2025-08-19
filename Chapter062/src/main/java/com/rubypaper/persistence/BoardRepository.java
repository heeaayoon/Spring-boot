package com.rubypaper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.domain.Board;
//Entity : DB의 테이블과 매핑되는 객체
//Repository :  Entity에 대한 데이터 접근을 추상화하여 CRUD(Create, Read, Update, Delete) 연산과 기본적인 쿼리 메소드를 제공하는 인터페이스
public interface BoardRepository extends JpaRepository<Board, Long> { 
	//JpaRepository<Entity의 클래스 타입, Primary key의 타입>
	//원래는 JpaRepository를 상속받은 BoardRepository에서 메소드 재정의를 해야하지만, 
	//스프링에서는 알라서 메소드 재정의를 해줌
	//각 메소드의 역할만 파악해두면 됨 -> JpaRepository 메소드 강의자료 참조
}

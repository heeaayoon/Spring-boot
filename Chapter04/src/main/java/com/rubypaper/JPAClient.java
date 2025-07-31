package com.rubypaper;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
//	public static void main(String[] args) {
//		//EntityManagerFactory  생성
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
//		
//		//EntityManager 생성
//		EntityManager em = emf.createEntityManager();
//		
//		// JPA가 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 반드시 트랜잭션 안에서 수행되어야 함
//		// Transaction 생성
//		EntityTransaction tx = em.getTransaction();
//		
//		try {
//			//Transaction 시작
//			tx.begin();
//			
//			//DB에 저장할 객체 생성
//			Board board = new Board();
//			board.setTitle("JPA제목");
//			board.setWriter("관리자");
//			board.setContent("JPA 글 등록");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			
//			//글 등록
//			em.persist(board);
//			
//			//정상 실행시 -> Trsaction 커밋
//			tx.commit();
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			//실행시 오류 -> Trsaction 롤백
//			tx.rollback();
//		}finally {
//			//사용한 리소스 객체 닫기
//			em.close();
//			emf.close();
//		}
//	}
	
//	public static void main(String[] args) {
//		//EntityManagerFactory  생성
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
//		
//		//EntityManager 생성
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			//글 상세 조회
//			Board searchBoard = em.find(Board.class,1L);
//			System.out.println("-->"+ searchBoard.toString());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			//사용한 리소스 객체 닫기
//			em.close();
//			emf.close();
//		}
//	}
	
	public static void main(String[] args) {
		//EntityManagerFactory  생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		try {
			List<Board> list = em.createQuery("select b from Board b", Board.class).getResultList();
			list.stream().forEach(System.out::println);
			
			@SuppressWarnings("Unchecked")
			List<Board> list1 = em.createNativeQuery("select * from board", Board.class).getResultList();
			list1.stream().forEach(System.out::println);
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//사용한 리소스 객체 닫기
			em.close();
			emf.close();
		}
	}

}

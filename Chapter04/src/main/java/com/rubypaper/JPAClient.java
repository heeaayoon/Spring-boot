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
	
//	public static void main(String[] args) {
//		//EntityManagerFactory  생성
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
//		
//		//EntityManager 생성
//		EntityManager em = emf.createEntityManager();
//		
//		try {
//			List<Board> list = em.createQuery("select b from Board b", Board.class).getResultList();
//			list.stream().forEach(System.out::println);
//			
//			@SuppressWarnings("Unchecked")
//			List<Board> list1 = em.createNativeQuery("select * from board", Board.class).getResultList();
//			list1.stream().forEach(System.out::println);
//		
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
		
// <property name="hibernate.hbm2ddl.auto" value="create" /> 로 설정해둔 상태면 -> 기존테이블을 삭제하고 새로운 테이블을 생성하므로 insert()문을 반드시 제일 먼저 실행하기
		insert(emf);
//		select(emf);
//		update(emf);
		delete(emf);
		
		emf.close();
	}

	private static void insert(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// JPA가 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 반드시 트랜잭션 안에서 수행되어야 함
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin(); //Transaction 시작
			
			//DB에 저장할 객체 생성
			for(int i=1;i<=5;i++) {
				Board board = new Board();
				board.setTitle("JPA제목");
				board.setWriter("관리자");
				board.setContent("JPA 글 등록");
				board.setCreateDate(new Date());
				board.setCnt(0L);
				//글 등록
				em.persist(board);
			}
			tx.commit(); //정상 실행시 -> Trsaction 커밋
		}catch(Exception e) {
			tx.rollback(); //실행시 오류 -> Trsaction 롤백
			e.printStackTrace();
		}finally {
			//사용한 리소스 객체 닫기
			em.close();
		}
	}

	private static void select(EntityManagerFactory emf) {
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
		}
	}

	//엔티티 삭제
	private static void delete(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// JPA가 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 반드시 트랜잭션 안에서 수행되어야 함
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin(); //Transaction 시작
			
			//삭제할 게시글 조회
			Board board = em.find(Board.class, 1L);
			em.remove(board);
			
			tx.commit(); //정상 실행시 -> Trsaction 커밋
		}catch(Exception e) {
			tx.rollback(); //실행시 오류 -> Trsaction 롤백
			e.printStackTrace();
		}finally {
			//사용한 리소스 객체 닫기
			em.close();
		}
	}
	
	//엔티티 수정
	private static void update(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		// JPA가 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 반드시 트랜잭션 안에서 수행되어야 함
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			//Transaction 시작
			tx.begin();
			
			//수정할 게시글 조회
			Board board = em.find(Board.class, 2L);
			board.setTitle("검색한 게시글의 제목 수정");
			tx.commit(); //정상 실행시 -> Trsaction 커밋
		}catch(Exception e) {
			tx.rollback(); //실행시 오류 -> Trsaction 롤백
			e.printStackTrace();
		}finally {
			//사용한 리소스 객체 닫기
			em.close();
		}
	}
}

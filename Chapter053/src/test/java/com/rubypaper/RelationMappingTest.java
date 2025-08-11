package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;

//	@Test //게시글 등록 테스트
	public void testManyToOneInsert1() {
		Member member1 = new Member(); // member1 생성
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		memberRepo.save(member1); // member1 저장

		Member member2 = new Member(); // member2 생성
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
		memberRepo.save(member2); // member2 저장

		for (int i = 1; i <= 3; i++) {
			Board board = new Board(); // board 생성
			board.setMember(member1); // member1설정
			board.setTitle("둘리가 등록한 게시글" + i);
			board.setContent("둘리가 등록한 게시글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board); // board 저장
		}
		for (int i = 1; i <= 3; i++) {
			Board board = new Board(); // board 생성
			board.setMember(member2); // member2설정
			board.setTitle("도우너가 등록한 게시글" + i);
			board.setContent("도우너가 등록한 게시글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board); // board 저장
		}
	}

//	@Test
//  @Transactional
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("[" + board.getSeq() + "번 게시글 정보]");
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		System.out.println("작성자 : " + board.getMember().getName());
		System.out.println("작성자 권한 : " + board.getMember().getRole());
	}

//	@Test //StackOverflowError 발생 <- Lombok의 @ToString으로 인한 순환참조
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member1").get();
		System.out.println("------------");
		System.out.println(member.getName() + "가 저장한 게시글 목록");
		System.out.println("------------");
		List<Board> list = member.getBoardList();
		for (Board board : list) {
			System.out.println(board.toString());
		}
	}

//	@Test // 영속성 전이를 이용해서 등록
	public void testManyToOneInsert2() {
		Member member1 = new Member(); // member1 생성
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		// memberRepo.save(member1); // member1 저장 X

		Member member2 = new Member(); // member2 생성
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
		// memberRepo.save(member2); // member2 저장 X

		for (int i = 1; i <= 3; i++) {
			Board board = new Board(); // board 생성
			board.setMember(member1); // member1 설정
			board.setTitle("둘리가 등록한 게시글" + i);
			board.setContent("둘리가 등록한 게시글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			// boardRepo.save(board); // board 저장 X
		}
		memberRepo.save(member1); //member만 저장하면 연결된 entitiy인 board도 저장이 됨
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board(); // board 생성
			board.setMember(member2); // member2 설정
			board.setTitle("도우너가 등록한 게시글" + i);
			board.setContent("도우너가 등록한 게시글 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			//boardRepo.save(board); // board 저장 X
		}
		memberRepo.save(member2); //member 저장하면 board 저장이 됨
	}
	
	@Test // 영속성 전이를 이용해서 제거
	public void testCascadeDelete() {
		memberRepo.deleteById("member2"); //member 삭제하면 board도 같이 삭제됨
	}

}

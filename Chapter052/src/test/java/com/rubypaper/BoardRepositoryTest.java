package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

		@Autowired
		private BoardRepository boardRepo;
		
		//@Test //어노테이션 부분만 주석달면 됨!
		//insert(작성)
		public void testInsertBoard() {
			Board board = new Board();
			board.setTitle("첫번째 게시글");
//			board.setWriter("테스터");
			board.setContent("잘 등록되나요?");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
		@Test
		//select(조회)
		public void testGetBoard() {
			Board board = boardRepo.findById(2L).get();
			System.out.println(board);
		}
		
		//@Test
		//update(수정)
		public void testUpdateBoard() {
			System.out.println("== 1번 게시글 조회 ==");
			Board board = boardRepo.findById(1L).get();
			System.out.println("== 게시글 제목 수정 ==");
			board.setTitle("제목을 수정했습니다");
			boardRepo.save(board);
		}
		
		@Test
		//delete(삭제)
		public void testDeleteBoard() {
			boardRepo.deleteById(1L);
		}
}

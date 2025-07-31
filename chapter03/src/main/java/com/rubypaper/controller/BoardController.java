package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {
	public BoardController() {
		System.out.println("==> boardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "hello : " + name;
	}
	
//	@GetMapping("/getBoard")  //VO 객체를 리턴하는 경우
//	public BoardVO getBoard() {
////		BoardVO board = new BoardVO();
////		board.setSeq(1);
////		board.setTitle("테스터제목");
////		board.setWriter("테스터");
////		board.setContent("내용");
////		board.setCreateDate(new Date());
////		board.setCnt(0);
//		BoardVO board = BoardVO.builder() //Builder Pattern을 이용한 BoardVO 객체생성
//				.seq(1)
//				.title("테스터제목입니다")
//				.writer("테스터")
//				.content("내용")
//				.createDate(new Date())
//				.cnt(0)
//				.build();
//		return board;
//	}
	
//	@GetMapping("/getBoard")
//	public BoardVO board(Integer seq) {
//		BoardVO board = new BoardVO();
//		board.setSeq(seq);
//		board.setWriter("테스터");
//		return board;
//	}
	
	@GetMapping("/getBoard")
	public BoardVO board() {
		BoardVO board = new BoardVO();
		board.setWriter("테스터");
		return board;
	}
	
	@GetMapping("/getBoardList")  //컬렉션을 리턴하는 경우
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		for(int i=1;i<=10;i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("테스터제목");
			board.setWriter("테스터");
			board.setContent(i+"내용");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}
	
}

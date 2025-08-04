package com.rubypaper.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	//전체 검색(read - select All)
	public List<Board> getboards(){
		List<Board> list = boardRepo.findAll();
		return list;
	}
	
	@GetMapping("/board/{seq}")
	//일부만 검색(read - select One)
	public Board getBoard(@PathVariable Long seq){
		Board board = boardRepo.findById(seq).get();
		System.out.println(board);
		return board;
	}
	
	@PostMapping("/board")
	//입력(Create - insert)
	public Board postBoard(@RequestBody Board board) {
		return boardRepo.save(board);
	}
	
	@PutMapping("/board/{seq}")
	//전체 수정(put - 객체 교체)
	public Board putBoard(@PathVariable Long seq, @RequestBody Board board) {
		board = boardRepo.findById(seq).get(); //seq번째 게시글 조회 
		System.out.println("== 게시글 전체 수정 ==");
		board.setSeq(seq); //seq,CreateDate,Cnt는 바꿀 수 없음
		board.setCreateDate(boardRepo.getCreateDate());
		boardRepo.
		return boardRepo.save(board);
	}
	
	@PatchMapping("/board/{seq}")
	//일부만 수정(patch - 일부 정보만 교체)
	public Board patchBoard(@PathVariable Long seq, @RequestBody Board board) {
		return null;
	}

	@DeleteMapping("/board/{seq}")
	//삭제(Delete - delete)
	public Board deleteBoard(@PathVariable Long seq) {
		//Board board = 
		//boardRepo.deleteById(seq);
		return null;
	}
	
}

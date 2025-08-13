package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rubypaper.domain.Board;
import com.rubypaper.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = boardService.getBoardList();
//
//		model.addAttribute("boardList", boardList); //Request 영역에 속성으로 저장됨
//		return "getBoardList";
//	}
	
	@RequestMapping(value="/getBoardList", method = {RequestMethod.GET, RequestMethod.POST}) //get, post 방식 둘 다 사용 가능해짐
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();

		model.addAttribute("boardList", boardList); //Request 영역에 속성으로 저장됨
		return "getBoardList";
	}
		
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard"; //WEB-INF 아래의 insertBoard.jsp 호출
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board); //BoardService 아래의 BoardServiceImpl의 insertBoard() 호출
		return "redirect:getBoardList"; //WEB-INF 아래의 getBoardList.jsp 호출(등록한 내용이 추가되어 있음)
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model){
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board); //BoardService 아래의 BoardServiceImpl의 updateBoard() 호출
		return "forward:getBoardList";
	}
	
	@GetMapping("deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board); //BoardService 아래의 BoardServiceImpl의 deleteBoard() 호출
		return "forward:getBoardList";
	}
	
}

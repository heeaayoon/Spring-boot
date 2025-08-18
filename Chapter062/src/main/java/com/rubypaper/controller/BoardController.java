package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@SessionAttributes("member") // Model 객체에 "member"라는 이름으로 저장되는 데이터를 자동으로 Session에 등록하도록 지시하는 설정
@Controller //요청을 받을 준비가 된 객체(빈)로 등록됨
public class BoardController {
	
	@Autowired  //BoardService(컨트롤러가 수행해야 할 "요청 처리"에 필요한 핵심 메서드들을 가지고 있음)를 의존성 주입
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member(); //세션에 
	}
	
//	@GetMapping("/getBoardList") //사용자가 ".../getBoardList" 주소를 요청하면
//	public String getBoardList(Model model) { //BoardController의 getBoardList()메소드를 호출하면서 빈 Model 객체를 전달
//		List<Board> boardList = boardService.getBoardList(); //BoardService에서 getBoardList()를 요청
//
//		model.addAttribute("boardList", boardList);  //addAttribute: 컨트롤러(Controller)가 뷰(View)에게 전달할 데이터를 "이름표를 붙여서" 넣어주는 메소드
//		return "getBoardList"; //뷰의 이름과 동일(.jsp)
//	}
	
	@RequestMapping(value="/getBoardList", method = {RequestMethod.GET, RequestMethod.POST}) //get, post 방식 둘 다 사용 가능해짐
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		model.addAttribute("boardList", boardService.getBoardList()); //로그인이 된 상태
		return "getBoardList";
	}
		
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		return "insertBoard"; //WEB-INF 아래의 insertBoard.jsp 호출
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		boardService.insertBoard(board); //BoardService 아래의 BoardServiceImpl의 insertBoard() 호출
		return "redirect:getBoardList"; //WEB-INF 아래의 getBoardList.jsp 호출(등록한 내용이 추가되어 있음)
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model){
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		boardService.updateBoard(board); //BoardService 아래의 BoardServiceImpl의 updateBoard() 호출
		return "forward:getBoardList";
	}
	
	@GetMapping("deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId()==null) { //로그인이 안된 상태
			return "redirect:login";
		}
		boardService.deleteBoard(board); //BoardService 아래의 BoardServiceImpl의 deleteBoard() 호출
		return "forward:getBoardList";
	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello Thymeleaf");
	}
	
}

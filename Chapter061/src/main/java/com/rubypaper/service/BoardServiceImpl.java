package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Service //빈 객체 생성
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}

	@Override
	public Board getBoard(Board board) {
		return null;
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);  //insert받은 내용을 Repo에 저장
	}

	@Override
	public void updateBoard(Board board) {
		
	}

	@Override
	public void deleteBoard(Board board) {
		
	}

}

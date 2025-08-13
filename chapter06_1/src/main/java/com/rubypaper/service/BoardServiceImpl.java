package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired 
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}

	@Override
	public Board getBoard(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

}

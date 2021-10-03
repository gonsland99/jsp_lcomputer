package com.gon.service;

import java.util.ArrayList;
import com.gon.dao.BoardDAO;
import com.gon.vo.Board;

public class BoardService {
	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {}
	public static BoardService getInstance() {	//singleton: 처음사용자만 객체생성 그후 사용자들 객체생성x 바로 return(메모리관리 용이)
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	public ArrayList<Board> getUsers(int page){
		return dao.getUsers(page);
	}
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	public int getUsersCount() {
		return dao.getUsersCount();
	}
}

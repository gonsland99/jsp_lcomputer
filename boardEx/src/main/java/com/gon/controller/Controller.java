package com.gon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gon.service.BoardService;
import com.gon.vo.Board;
import com.gon.vo.Pagination;

@WebServlet("*.do2")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();	//boardEx.*do2
		String contextPath = request.getContextPath();	//boardEx
		String command = requestURI.substring(contextPath.length());
		String view = null;	//경로이동용 변수
		int page = 1;	//페이징처리용 변수
		String reqPage = null;	//고객이 요청하는 페이지
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch(command) {
		case "/board-list.do2":
			reqPage = request.getParameter("page");
			if(reqPage != null) {
				page = Integer.parseInt(reqPage);
			}
			
			BoardService bService = BoardService.getInstance();
			int count = bService.getUsersCount();
			ArrayList<Board> bList = bService.getUsers(page);
			Pagination pagination = new Pagination(page, count);
			
			request.setAttribute("bList", bList);
			request.setAttribute("pagination", pagination);
			view = "board/list";
			break;
		case "/board-insert.do2":
			view = "board/insert";
			break;
		case "/board-insert-process.do2":
			Board board = new Board();
			board.setB_writer(request.getParameter("writer"));
			board.setB_title(request.getParameter("title"));
			board.setB_content(request.getParameter("content"));
			
			bService = BoardService.getInstance();	//insertUser를 사용하기 위해 userDAO 가져옴
			bService.insertBoard(board);
			view = "board/insert-result";
			break;
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}

}

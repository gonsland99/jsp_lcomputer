package com.mvc.controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;		//java api 왜 없지?
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.service.UserService;
import com.mvc.vo.Pagination;
import com.mvc.vo.User;

@WebServlet("*.do")
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		HttpSession session = null;
		String requestURI = request.getRequestURI();	//jsp_basic/*.do
		String contextPath = request.getContextPath();	//jsp_basic
		String command = requestURI.substring(contextPath.length());	//jsp_basic 뒤의 주소값(.do)
		String view = null;
		String reqPage;
		User user = null;
		int usercount = 0;
		int page = 1;
		command = checkSession(request, response, command);	//
		int dIdx = 0;	//detail Index
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch(command) {
		case "/user-list.do":
			//reqPage를 외부에서도 사용하려면 외부에 변수를 생성후 사용하는게 좋음
			reqPage = request.getParameter("page");	//page값이란게 user-list.do?page=${pagination.prevPage }?
			if(reqPage != null) {
				page = Integer.parseInt(reqPage);
			}
			
			UserService userService = UserService.getInstance();	//singleton
			int count = userService.getUsersCount();
			ArrayList<User> list = userService.getUsers(page);
			Pagination pagination = new Pagination(page, count);
//			pagination.setCount(count);	//pagination 생성시 count 값이 없기 때문에 페이징 출력 안됨
			//해결방법 1. 위와 같이 생성자에 count를 받아서 사용
			//해결방법 2. 기본생성자 생성후 count값을 setCount로 받고 page값을 받는 생성자를 호출
			//	pagination.setCount(count);
			
//			System.out.println(pagination.getEndPage());
			
			request.setAttribute("list", list);	// 객체로 request에 넣음 (단일변수는 setParmeter())
			request.setAttribute("pagination",pagination);
			view = "user/list";
			break;
			
		case "/user-insert.do":
			view = "user/insert";
			break;
		case "/user-insert-process.do":
			user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));
			
			userService = UserService.getInstance();	//insertUser를 사용하기 위해 userDAO 가져옴
			userService.insertUser(user);
			view = "user/insert-result";
			break;
		case "/user-login.do":
			view = "user/login";
			break;
		case "/user-login-process.do":
			String idx = request.getParameter("login-id");
			String pw = request.getParameter("login-password");
			userService = UserService.getInstance();
			user = userService.loginUser(idx, pw);
			
			if(user != null) {
				session = request.getSession();	//서버정보 다시 받아옴
				session.setAttribute("u_idx", user.getU_idx());
				session.setAttribute("u_id", user.getU_id());
				session.setAttribute("u_pw", user.getU_pw());
				session.setAttribute("u_name", user.getU_name());
				session.setAttribute("user",user);
				view = "user/login-result";
			} else {
				view = "user/login-fail";
			}
			break;
		case "/logout.do":
			session = request.getSession();
			session.invalidate();	//세션 초기화
			view = "user/login";
			break;
		case "/access-denied.do":
			view = "user/access-denied";
			break;
		case "/user-detail.do":
			String reqIdx = request.getParameter("u_idx");
//			System.out.println(reqIdx);
			if(reqIdx != null) {
				dIdx = Integer.parseInt(reqIdx);
			}
			
			UserService userService2 = UserService.getInstance();
			ArrayList<User> dList = userService2.getUserDetail(dIdx);
//			System.out.println(dList.toString());
			request.setAttribute("dList", dList);	

			view = "user/detail";
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do",
//				"/user-insert.do",
//				"/user-insert-process.do",
				"/user-detail.do",
//				"/user-edit.do",
//				"/user-edit-process.do",
				"/logout.do"
		};
		
		for(String item : authList) {
			if(item.equals(command)) {
				if(session.getAttribute("user") == null) {	//user값이 있으면(로그인 상태이면)
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}

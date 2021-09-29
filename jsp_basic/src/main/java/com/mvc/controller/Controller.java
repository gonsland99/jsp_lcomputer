package com.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String requestURI = request.getRequestURI();	//jsp_basic/*.do
		String contextPath = request.getContextPath();	//jsp_basic
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		int usercount = 0;
		int page = 1;
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		switch(command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if(reqPage != null) {
				page = Integer.parseInt(reqPage);
			}
			
			UserService userService = UserService.getInstance();	//singleton
			ArrayList<User> list = userService.getUsers(page);
			Pagination pagination = new Pagination(page);
			pagination.setCount(userService.getUsersCount());
//			System.out.println(pagination.getEndPage());
			
			request.setAttribute("list", list);
			request.setAttribute("pagination",pagination);
			view = "user/list";
			break;
			
		case "/user-insert.do":
			view = "user/insert";
			break;
		case "/user-insert-process.do":
			User user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));
			
			userService = UserService.getInstance();	//insertUser를 사용하기 위해 userDAO 가져옴
			userService.insertUser(user);
			view = "user/insert-result";
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
}

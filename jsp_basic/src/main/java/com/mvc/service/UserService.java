package com.mvc.service;

import java.util.ArrayList;

import com.mvc.dao.UserDAO;
import com.mvc.vo.User;

public class UserService{
	//매게변수를 두지 않고 사용자마다 다른 값을 각자 가지고감
	
	private static UserService service = null;
	private static UserDAO dao = null;
	
	public UserService() {}
	public static UserService getInstance() {	//singleton
	//처음 사용자 외에 사용자들은 객체생성을 별도로 가져가지 않아 메모리관리 용이(바로 return함)
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance();
		}
		return service;
	}
	public ArrayList<User> getUsers(int page){
		return dao.getUsers(page);
	}
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	public int getUsersCount() {
		return dao.getUsersCount();
	}
}

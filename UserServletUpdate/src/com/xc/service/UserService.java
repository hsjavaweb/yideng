package com.xc.service;

import java.sql.SQLException;

import com.xc.dao.UserDao;
import com.xc.domain.User;

public class UserService {

	UserDao ud = new UserDao();
	public void addUser(User user) {
		try {
			ud.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public User findUser(String username, String password) {
		try {
			return ud.findUSer(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

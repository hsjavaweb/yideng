package com.xc.service.impl;

import com.xc.dao.UserDao;
import com.xc.dao.impl.UserDaoImpl;
import com.xc.domain.User;
import com.xc.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao ud = new UserDaoImpl();
	public void addUser(User user) {
		ud.addUser(user);
	}
	public boolean findUser(String username, String password) {
		return ud.findUSer(username, password);
	}
}

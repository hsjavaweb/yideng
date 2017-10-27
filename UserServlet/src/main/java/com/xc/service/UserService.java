package com.xc.service;

import com.xc.domain.User;

public interface UserService {

	/**
	 * 用户添加
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 通过用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean findUser(String username, String password);
}

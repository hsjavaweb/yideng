package com.xc.dao;

import com.xc.domain.User;

public interface UserDao {

	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean findUSer(String username, String password);
}

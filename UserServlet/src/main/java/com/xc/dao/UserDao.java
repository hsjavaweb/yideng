package com.xc.dao;

import com.xc.domain.User;

public interface UserDao {

	/**
	 * ����û�
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * �����û�
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean findUSer(String username, String password);
}

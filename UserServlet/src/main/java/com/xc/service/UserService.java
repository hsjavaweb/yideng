package com.xc.service;

import com.xc.domain.User;

public interface UserService {

	/**
	 * �û����
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * ͨ���û�������������û�
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean findUser(String username, String password);
}

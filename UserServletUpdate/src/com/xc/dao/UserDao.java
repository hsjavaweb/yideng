package com.xc.dao;



import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xc.domain.User;
import com.xc.utils.C3P0Util;

public class UserDao{
	
	public void addUser(User user) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into user(username,password,email,sex,hobbys) values(?,?,?,?,?)",
				user.getUsername(),user.getPassword(),user.getEmail(),user.getSex(),user.getHobbys());
	}

	public User findUSer(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		System.out.println("------------");
		return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),username,password);

	}

}
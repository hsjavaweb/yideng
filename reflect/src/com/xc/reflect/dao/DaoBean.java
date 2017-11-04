package com.xc.reflect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.xc.reflect.domain.User;
import com.xc.reflect.utils.C3P0Util;

public class DaoBean extends Dao<User>{
	
	@Test
	public void test() throws Exception{

		User u = new User();
		u.setUsername("tom");
		u.setPassword("123");
		
		List<User> list = selectAll(u);
		if(list!=null){
			for (User user : list) {
				System.out.println(user);
			}
		}
		
		User users = new User();
		users.setUsername("tom");
		users.setPassword("123");
		List<User> lists = selectAll(users);
		if(list!=null){
			for (User users1 : lists) {
				System.out.println(users1);
			}
		}
	
	}
}

class Dao<T>{ 
	
/*	//T  编译时，注释变量。在运行时，才可以获得具体的类型
	private Class<?> beanClass;
	public Dao() {
		//获得运行时的类型 , BaseDaoImpl<CrmStaff>被参数化的类型
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得实际参数的 ,获得所有。此时只有一个
		beanClass = (Class<?>) paramType.getActualTypeArguments()[0];
		System.out.println(beanClass);
	}*/
	Connection conn = null;
	PreparedStatement ps = null;
	public List<T> selectAll(Object obj){
		//等到类名
		String name = obj.getClass().getSimpleName();
		System.out.println(name);
		conn = C3P0Util.getConnection();
		try {
			ps = conn.prepareStatement("select * from "+name);
			ResultSet rs = ps.executeQuery();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3P0Util.release(conn, ps, null);
		}
		return null;
	}
}
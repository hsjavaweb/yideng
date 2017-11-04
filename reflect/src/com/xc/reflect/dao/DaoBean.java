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
	
/*	//T  ����ʱ��ע�ͱ�����������ʱ���ſ��Ի�þ��������
	private Class<?> beanClass;
	public Dao() {
		//�������ʱ������ , BaseDaoImpl<CrmStaff>��������������
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//���ʵ�ʲ����� ,������С���ʱֻ��һ��
		beanClass = (Class<?>) paramType.getActualTypeArguments()[0];
		System.out.println(beanClass);
	}*/
	Connection conn = null;
	PreparedStatement ps = null;
	public List<T> selectAll(Object obj){
		//�ȵ�����
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
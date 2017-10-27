package com.xc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xc.dao.UserDao;
import com.xc.domain.User;
import com.xc.utils.DBUtils;

public class UserDaoImpl implements UserDao{

	Connection conn = null;
	ResultSet  rs = null;
	PreparedStatement ps = null;
	public void addUser(User user) {
		try {
			//1.�õ�����
			conn = DBUtils.getConnection();
			//2.�õ�Ԥ�������
			String sql = "insert into user(username,password,email,sex,hobbys) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			//������ֵ
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSex());
			//��������hobbys��ֵ
			String str = "";
			String[] hobbys = user.getHobbys();
			for(int i = 0; hobbys!=null && i < hobbys.length; i++){
				str+=hobbys[i];
				if(i!=hobbys.length-1){
					str+=", ";
				}
			}
			ps.setString(5, str);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			DBUtils.closeAll(rs, ps, conn);
		}
	}
	public boolean findUSer(String username, String password){
		try {
			conn = DBUtils.getConnection();
			String sql  = "select * from user where username=? and password=?";
			//����Ԥ�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet user = ps.executeQuery();
			if(user.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return false;
	}
}
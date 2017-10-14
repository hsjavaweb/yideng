package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.alibaba.Bean.User;

public class Test3 {
	
	//��ȷ�ر���Դ
	@Test
	public void test1(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2��������������
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/yideng","root","root");
			//3���õ�ִ��sql����Statement
			stmt = conn.createStatement();
			//4.ִ��sql���
			String sql = "select * from users";
			rs = stmt.executeQuery(sql);
			//5.����н����������
			boolean b = rs.previous();
			if(b){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

import com.alibaba.Bean.User;
import com.alibaba.util.DBUtils;

public class Test4 {

	//模拟用户登录
	/*问题:如当密码输入  ss' or '1'='1  字符串时
		sql语句租转成: select * from users where username='username' and password='ss' or '1'='1 ';
		用户就可以随意登录
	*/
	@Test
	public void login() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户名！");
		String username = sc.nextLine();
		System.out.println("请输入密码！");
		String password = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtils.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from users where username='"+username+"' and password='"+password+"'";
			
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println("欢迎您:"+username);
			}else{
				System.out.println("用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, stmt, conn);
		}
	}
}

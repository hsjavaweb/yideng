package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.junit.Test;

import com.alibaba.util.DBUtils;

public class Test5 {

	//解决问题 :用preparedStatement:预编译对象, 是Statement对象的子类
	@Test
	public void login(){
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户名！");
		String username = sc.nextLine();
		System.out.println("请输入密码！");
		String password = sc.nextLine();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//得到连接
			conn = DBUtils.getConnection();
			String sql = "select * from users where username=? and password=?";
			ps = conn.prepareStatement(sql);//创建此对象是，要把sql语句放进去
			//给预编译的sql语句赋值
			ps.setString(1, username);//1 代表sql语句中的第一个？
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("欢迎您:"+username);
			}else{
				System.out.println("用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		
		
	}
}

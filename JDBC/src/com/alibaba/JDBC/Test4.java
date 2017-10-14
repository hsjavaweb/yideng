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

	//ģ���û���¼
	/*����:�統��������  ss' or '1'='1  �ַ���ʱ
		sql�����ת��: select * from users where username='username' and password='ss' or '1'='1 ';
		�û��Ϳ��������¼
	*/
	@Test
	public void login() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("�������û�����");
		String username = sc.nextLine();
		System.out.println("���������룡");
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
				System.out.println("��ӭ��:"+username);
			}else{
				System.out.println("�û������������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, stmt, conn);
		}
	}
}

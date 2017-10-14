package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test1 {

	/**
	 * ResultSet executeQuery(String sql)
	 * ���ݲ�ѯ��䷵�ؽ������ֻ��ִ��select��䡣
 	 * int executeUpdate(String sql) 
 	 * ����ִ�е�DML��insert update delete����䣬������Ӱ���������
 	 * boolean execute(String sql)  
 	 * �˷�������ִ������sql��䡣����booleanֵ����ʾ�Ƿ񷵻�ResultSet�����������ִ��select��䣬���з��ؽ��ʱ����true, ������䶼����false;
	 * @param args
	 * @throws SQLException 
	 */
	//JDBC������
	public static void main(String[] args) throws SQLException {

		//1.ע������
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2.��������
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/yideng","root","root");
		//3.�õ��������ݿ�sql���Ķ���Statement
		Statement stmt =  conn.createStatement();
		//4.ִ��sql���
		String sql = "select * from users";
		//���²��������³ɹ������ܵ�Ӱ�������  
		int i = stmt.executeUpdate("update users set username='liu' where password = '123'");
		System.out.println(i);
		boolean j = stmt.execute(sql);
		System.out.println(j);
		ResultSet rs = stmt.executeQuery(sql);
		//5.����н������������
		while(rs.next()){
			System.out.println(rs.getObject(1));
			System.out.println(rs.getObject(2));
			System.out.println(rs.getObject(3));
			System.out.println(rs.getObject(4));
			System.out.println(rs.getObject(5));
			System.out.println("--------------");
		}
		//6.�ر���Դ
		rs.close();
		stmt.close();
		conn.close();
	}

}

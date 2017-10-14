package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test1 {

	/**
	 * ResultSet executeQuery(String sql)
	 * 根据查询语句返回结果集。只能执行select语句。
 	 * int executeUpdate(String sql) 
 	 * 根据执行的DML（insert update delete）语句，返回受影响的行数。
 	 * boolean execute(String sql)  
 	 * 此方法可以执行任意sql语句。返回boolean值，表示是否返回ResultSet结果集。仅当执行select语句，且有返回结果时返回true, 其它语句都返回false;
	 * @param args
	 * @throws SQLException 
	 */
	//JDBC初体验
	public static void main(String[] args) throws SQLException {

		//1.注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2.创建连接
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/yideng","root","root");
		//3.得到操作数据库sql语句的对象Statement
		Statement stmt =  conn.createStatement();
		//4.执行sql语句
		String sql = "select * from users";
		//更新操作，更新成功返回受到影响的行数  
		int i = stmt.executeUpdate("update users set username='liu' where password = '123'");
		System.out.println(i);
		boolean j = stmt.execute(sql);
		System.out.println(j);
		ResultSet rs = stmt.executeQuery(sql);
		//5.如果有结果集，处理结果
		while(rs.next()){
			System.out.println(rs.getObject(1));
			System.out.println(rs.getObject(2));
			System.out.println(rs.getObject(3));
			System.out.println(rs.getObject(4));
			System.out.println(rs.getObject(5));
			System.out.println("--------------");
		}
		//6.关闭资源
		rs.close();
		stmt.close();
		conn.close();
	}

}

package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.Bean.User;

public class Test2 {

	/**
	 * 封装数据的方法
	 	* 根据序号取值，索引从1开始
	 	* Object getObject(int columnIndex); 
	 	* 根据列名取值
	 	* Object getObject(String ColomnName); 
	 	* boolean next()	将光标从当前位置向下移动一行
	 	* int getInt(int colIndex)	以int形式获取ResultSet结果集当前行指定列号值
	 	* int getInt(String colLabel)	以int形式获取ResultSet结果集当前行指定列名值
	 	* float getFloat(int colIndex)	以float形式获取ResultSet结果集当前行指定列号值
	 	* float getFloat(String colLabel)	以float形式获取ResultSet结果集当前行指定列名值
	 	* String getString(int colIndex)	以String 形式获取ResultSet结果集当前行指定列号值
	 	* String getString(String colLabel)	以String形式获取ResultSet结果集当前行指定列名值
	 	* Date getDate(int columnIndex);  
	 	* Date getDate(String columnName);
	 	* void close()	关闭ResultSet 对象
	 	* boolean next()  将光标从当前位置向前移一行。 
	 	* boolean previous() 将光标移动到此 ResultSet 对象的上一行。 
	 	* boolean absolute(int row) 参数是当前行的索引，从1开始根据行的索引定位移动的指定索引行。
	 	* void afterLast() 将光标移动到末尾，正好位于最后一行之后。 
	 	* void beforeFirst() 将光标移动到开头，正好位于第一行之前。
	 * @param args
	 * @throws SQLException 
	 */
	//封装
	public static void main(String[] args) throws SQLException {
		//1.注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2.创建连接
		Connection conn =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/yideng","root","root");
		//3.得到操作数据库sql语句的对象Statement
		Statement stmt = (Statement) conn.createStatement();
		//4.执行sql语句
		String sql = "select * from users";
		ResultSet rs = stmt.executeQuery(sql);
		//5.如果有结果集，处理结果
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setBirthday(rs.getDate("birthday"));
			user.setEmail(rs.getString("email"));
			System.out.println(user);
		}
		//6.关闭资源
		rs.close();
		stmt.close();
		conn.close();
	}

}

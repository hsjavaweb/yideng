package com.xc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


	public class DBUtils {
		private static String driverClass;
		private static String url;
		private static String username;
		private static String password;
		static{
			//用于加载配置文件
			ResourceBundle rb = ResourceBundle.getBundle("database");
			driverClass = rb.getString("driverClass");
			url = rb.getString("url");
			username = rb.getString("username");
			password = rb.getString("password");
			try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	 
		public static Connection getConnection() throws SQLException{
			return DriverManager.getConnection(url,username,password);
		}
		
		public static void closeAll(ResultSet rs, Statement stmt, Connection conn){
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

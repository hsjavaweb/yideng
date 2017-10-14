package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.Bean.User;

public class Test2 {

	/**
	 * ��װ���ݵķ���
	 	* �������ȡֵ��������1��ʼ
	 	* Object getObject(int columnIndex); 
	 	* ��������ȡֵ
	 	* Object getObject(String ColomnName); 
	 	* boolean next()	�����ӵ�ǰλ�������ƶ�һ��
	 	* int getInt(int colIndex)	��int��ʽ��ȡResultSet�������ǰ��ָ���к�ֵ
	 	* int getInt(String colLabel)	��int��ʽ��ȡResultSet�������ǰ��ָ������ֵ
	 	* float getFloat(int colIndex)	��float��ʽ��ȡResultSet�������ǰ��ָ���к�ֵ
	 	* float getFloat(String colLabel)	��float��ʽ��ȡResultSet�������ǰ��ָ������ֵ
	 	* String getString(int colIndex)	��String ��ʽ��ȡResultSet�������ǰ��ָ���к�ֵ
	 	* String getString(String colLabel)	��String��ʽ��ȡResultSet�������ǰ��ָ������ֵ
	 	* Date getDate(int columnIndex);  
	 	* Date getDate(String columnName);
	 	* void close()	�ر�ResultSet ����
	 	* boolean next()  �����ӵ�ǰλ����ǰ��һ�С� 
	 	* boolean previous() ������ƶ����� ResultSet �������һ�С� 
	 	* boolean absolute(int row) �����ǵ�ǰ�е���������1��ʼ�����е�������λ�ƶ���ָ�������С�
	 	* void afterLast() ������ƶ���ĩβ������λ�����һ��֮�� 
	 	* void beforeFirst() ������ƶ�����ͷ������λ�ڵ�һ��֮ǰ��
	 * @param args
	 * @throws SQLException 
	 */
	//��װ
	public static void main(String[] args) throws SQLException {
		//1.ע������
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2.��������
		Connection conn =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/yideng","root","root");
		//3.�õ��������ݿ�sql���Ķ���Statement
		Statement stmt = (Statement) conn.createStatement();
		//4.ִ��sql���
		String sql = "select * from users";
		ResultSet rs = stmt.executeQuery(sql);
		//5.����н������������
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setBirthday(rs.getDate("birthday"));
			user.setEmail(rs.getString("email"));
			System.out.println(user);
		}
		//6.�ر���Դ
		rs.close();
		stmt.close();
		conn.close();
	}

}

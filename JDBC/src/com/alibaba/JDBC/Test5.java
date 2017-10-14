package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.junit.Test;

import com.alibaba.util.DBUtils;

public class Test5 {

	//������� :��preparedStatement:Ԥ�������, ��Statement���������
	@Test
	public void login(){
		Scanner sc = new Scanner(System.in);
		System.out.println("�������û�����");
		String username = sc.nextLine();
		System.out.println("���������룡");
		String password = sc.nextLine();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//�õ�����
			conn = DBUtils.getConnection();
			String sql = "select * from users where username=? and password=?";
			ps = conn.prepareStatement(sql);//�����˶����ǣ�Ҫ��sql���Ž�ȥ
			//��Ԥ�����sql��丳ֵ
			ps.setString(1, username);//1 ����sql����еĵ�һ����
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("��ӭ��:"+username);
			}else{
				System.out.println("�û������������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		
		
	}
}

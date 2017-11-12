package com.xc.yideng.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.xc.yideng.DBUtil.C3P0Util;
import com.xc.yideng.DBUtil.DBUtil;
import com.xc.yideng.DBUtil.ListBean;
import com.xc.yideng.DBUtil.ObjectBean;
import com.xc.yideng.domain.Student;

public class test {

	
	@Test
	public void test1() throws Exception{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		//创建成功返回0
		int i = db.createTable(Student.class);
		System.out.println(i);
	}
	
	@Test
	public void test3() throws Exception{
		Connection conn = C3P0Util.getConnection();
		DBUtil db = new DBUtil(conn);
		List<Student> lsit = db.findAll(new ListBean<Student>(Student.class));
		for (Student student : lsit) {
			System.out.println(student);
		}
	}
	
	@Test
	public void test4() throws Exception{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		String sql = "select * from student where id = ? ";
		Student student = db.find(sql,new ObjectBean<Student>(Student.class), 2);
		System.out.println(student);
		List<Student> list = db.find(sql,new ListBean<Student>(Student.class), 1);
		for (Student stu : list) {
			System.out.println(stu);
		}
	}
	
	@Test
	public void test5() throws Exception{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		String sql = "select * from student where id = ? ";
		Student student = db.find(sql,new ObjectBean<Student>(Student.class), 1);
		System.out.println(student);
	}
	
	@Test
	public void test6() throws Exception{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		String sql = "UPDATE student SET NAME=? WHERE id = ?;";
		int b = db.update(sql, "tom",1);
		System.out.println(b);
	}
	
	@Test
	public void test7() throws Exception{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		String sql = "INSERT INTO student(id, NAME) VALUES(?, ?);";
		int b = db.insert(sql, 5, "liuyong");
		System.out.println(b);
	}
	
	@Test 
	public void test8() throws SQLException{
		DBUtil db = new DBUtil(C3P0Util.getConnection());
		String sql = "DELETE FROM student WHERE id in(?,?);";
		//返回删除数据的条数
		int delete = db.delete(sql, 3,4);
		System.out.println(delete);
	}
}

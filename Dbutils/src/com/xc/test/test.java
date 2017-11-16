package com.xc.test;

import java.util.List;

import org.junit.Test;

import com.xc.domain.Student;
import com.xc.util.C3P0Util;
import com.xc.yideng.Dbutil;
import com.xc.yideng.DbutilImpl;

public class test {

	@Test
	public void test1(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		Student student = (Student) db.getByKey(Student.class, 2);
		System.out.println(student);
	}
	
	@Test
	public void test2(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		Student student = new Student("ÄãºÃ","33","22");
		Student s = new Student();
 		boolean b = db.insert(s);
		System.out.println(b);
	}
	
	@Test
	public void test3(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		boolean b = db.delByKey(Student.class, 12);
		System.out.println(b);
	}
	
	@Test
	public void test4(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		Student student = new Student("22", "33", "44");
		student.setId(7);
		boolean update = db.update(student);
		System.out.println(update);
	}
	@Test
	public void test5(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		List<Object> list = db.getAll(Student.class);
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	@Test
	public void test6(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		Student stu = new Student("tom","22","23");
		stu.setId(16);
		db.saveOrUpdate(stu);
	}
	
	@Test
	public void test7(){
		Dbutil db = new DbutilImpl(C3P0Util.getConnection());
		List<Object> list = db.get(Student.class,"name=?","tom");
		for (Object object : list) {
			System.out.println(object);
		}
	}
}

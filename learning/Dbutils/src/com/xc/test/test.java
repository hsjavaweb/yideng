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
		Dbutil db = new DbutilImpl();
		Student student = (Student) db.getByKey(Student.class, 2);
		System.out.println(student);
	}
	
	@Test
	public void test2(){
		Dbutil db = new DbutilImpl();
		Student student = new Student();
		student.setAge(22);
		student.setName("nihao");
		student.setPassword("123");
		boolean b = db.insert(student);
		System.out.println(b);
	}
	
	@Test
	public void test3(){
		Dbutil db = new DbutilImpl();
		boolean b = db.delByKey(Student.class, 12);
		System.out.println(b);
	}
	
	@Test
	public void test4(){
		Dbutil db = new DbutilImpl();
		Student stu = new Student();
		stu.setStudentId(7);
		stu.setAge(1);
		stu.setName("tom");
		stu.setPassword("222222");
		boolean update = db.update(stu);
		System.out.println(update);
	}
	@Test
	public void test5(){
		Dbutil db = new DbutilImpl();
		List<Object> list = db.getAll(Student.class);
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	@Test
	public void test7(){
		Dbutil db = new DbutilImpl();
		List<Object> list = db.get(Student.class,"name=?","tom");
		for (Object object : list) {
			System.out.println(object);
		}
	}
}

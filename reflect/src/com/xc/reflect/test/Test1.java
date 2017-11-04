package com.xc.reflect.test;

import java.lang.reflect.Field;

import org.junit.Test;

import com.xc.reflect.domain.User;

public class Test1 {

	@Test
	public void test1() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.domain.User");
		User user = (User) clazz.newInstance();
		test t = new test();
		t.setProperty(user, "username", "张三");
		System.out.println(user);
	}
}

//功能：给一个对象的指定字段赋值
class test{
	public void setProperty(Object obj, String propertyName, Object value) throws Exception{
		Class<?> clazz = obj.getClass();
		Field f = clazz.getDeclaredField(propertyName);
		f.setAccessible(true);
		f.set(obj, value);
	}
}
package com.xc.reflect.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import org.junit.Test;

import com.xc.reflect.aop.MyInvocationHandler;
import com.xc.reflect.aop.Student;
import com.xc.reflect.aop.StudentImpl;
import com.xc.reflect.domain.User;

public class reflect {

	/**
	 * 获取class文件对象的三种方式
	 * @throws Exception
	 */
	@Test
	public void Demo1() throws Exception{
		
		User user = new User();
		//通过三种凡方式获取字节码对象
		System.out.println(Class.forName("com.xc.reflect.domain.User"));
		System.out.println(User.class);
		System.out.println(user.getClass());
	}
	
	/**
	 * 通过反射获取无参或带有参构造方法并使用
	 * @throws Exception
	 */
	@Test
	public void Demo2() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.domain.User");
		//无参
		User user1 = (User) clazz.newInstance();
		System.out.println(user1);
		//有参
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
		User user2= (User) constructor.newInstance("张三", "123");
		System.out.println(user2);
	}
	
	/**
	 * 通过反射获取成员变量并使用
	 * @throws Exception
	 */
	@Test
	public void Demo3() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.domain.User");
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
		User user = (User) constructor.newInstance("张三", "123");
		//暴力反射得到字段名
		Field username = clazz.getDeclaredField("username");
		//去除私有权限
		username.setAccessible(true);
		username.set(user, "李四");
		System.out.println(user);
	}
	
	/**
	 * 通过反射获取方法，并使用
	 * @throws Exception
	 */
	@Test
	public void Demo4() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.User");
		User user = (User) clazz.newInstance();
		//获取非私有，不带参的方法
		Method method = clazz.getMethod("learning");
		method.invoke(user);
		//获取非私有，带参数方法
		Method method2 = clazz.getMethod("learning", int.class);
		method2.invoke(user, 10);
		
		//获取私有
		Method method3 = clazz.getDeclaredMethod("learning");
		method3.invoke(user);

		Method method4 = clazz.getDeclaredMethod("learning", int.class);
		method4.invoke(user, 12);
		
	}
	
	/**
	 * 通过反射越过泛型检查
	 * @throws Exception
	 */
	@Test
	public void Demo5() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		list.add(11);
		
		Class<?> clazz = Class.forName("java.util.ArrayList");
		Method method = clazz.getDeclaredMethod("add", Object.class);
		method.invoke(list, "abc");
		
		System.out.println(list);
	}
	//通过反射生成代理
	@Test
	public void Demo6(){
		StudentImpl si = new StudentImpl();
		MyInvocationHandler m  = new MyInvocationHandler(si);
		//返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序
		Student stu = (Student) Proxy.newProxyInstance(si.getClass().getClassLoader(), si.getClass().getInterfaces(), m);
		stu.add();
		stu.delete();
	}
}

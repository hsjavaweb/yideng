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
	 * ��ȡclass�ļ���������ַ�ʽ
	 * @throws Exception
	 */
	@Test
	public void Demo1() throws Exception{
		
		User user = new User();
		//ͨ�����ַ���ʽ��ȡ�ֽ������
		System.out.println(Class.forName("com.xc.reflect.domain.User"));
		System.out.println(User.class);
		System.out.println(user.getClass());
	}
	
	/**
	 * ͨ�������ȡ�޲λ���вι��췽����ʹ��
	 * @throws Exception
	 */
	@Test
	public void Demo2() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.domain.User");
		//�޲�
		User user1 = (User) clazz.newInstance();
		System.out.println(user1);
		//�в�
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
		User user2= (User) constructor.newInstance("����", "123");
		System.out.println(user2);
	}
	
	/**
	 * ͨ�������ȡ��Ա������ʹ��
	 * @throws Exception
	 */
	@Test
	public void Demo3() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.domain.User");
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
		User user = (User) constructor.newInstance("����", "123");
		//��������õ��ֶ���
		Field username = clazz.getDeclaredField("username");
		//ȥ��˽��Ȩ��
		username.setAccessible(true);
		username.set(user, "����");
		System.out.println(user);
	}
	
	/**
	 * ͨ�������ȡ��������ʹ��
	 * @throws Exception
	 */
	@Test
	public void Demo4() throws Exception{
		Class<?> clazz = Class.forName("com.xc.reflect.User");
		User user = (User) clazz.newInstance();
		//��ȡ��˽�У������εķ���
		Method method = clazz.getMethod("learning");
		method.invoke(user);
		//��ȡ��˽�У�����������
		Method method2 = clazz.getMethod("learning", int.class);
		method2.invoke(user, 10);
		
		//��ȡ˽��
		Method method3 = clazz.getDeclaredMethod("learning");
		method3.invoke(user);

		Method method4 = clazz.getDeclaredMethod("learning", int.class);
		method4.invoke(user, 12);
		
	}
	
	/**
	 * ͨ������Խ�����ͼ��
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
	//ͨ���������ɴ���
	@Test
	public void Demo6(){
		StudentImpl si = new StudentImpl();
		MyInvocationHandler m  = new MyInvocationHandler(si);
		//����һ��ָ���ӿڵĴ�����ʵ�����ýӿڿ��Խ���������ָ�ɵ�ָ���ĵ��ô������
		Student stu = (Student) Proxy.newProxyInstance(si.getClass().getClassLoader(), si.getClass().getInterfaces(), m);
		stu.add();
		stu.delete();
	}
}

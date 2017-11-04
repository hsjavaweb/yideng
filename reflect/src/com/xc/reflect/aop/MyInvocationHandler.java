package com.xc.reflect.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;
	public MyInvocationHandler(Object target){
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) 
			throws Throwable {
		System.out.println("ִ�з���ǰ");
		method.invoke(target, args);
		System.out.println("ִ�з�����");
		return null;
	}

}
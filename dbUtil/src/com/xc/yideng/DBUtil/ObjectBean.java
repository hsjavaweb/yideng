package com.xc.yideng.DBUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class ObjectBean<T> {

private Class<?> clazz;
	
	public ObjectBean(Class<?> clazz){
		this.clazz = clazz;
	}
	/**
	 * 封装结果集(只有一个结果集)
	 * @param rs
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T beanList(ResultSet rs,Class<?> clazz) throws Exception{
        
        Field[] fields = clazz.getDeclaredFields();
        Object object = null;
        while (rs.next()) { 
        	object = clazz.newInstance();
        	for(int i = 1; i <= fields.length; i++){
        		Object value = rs.getObject(i);
        		boolean flag = fields[i-1].isAccessible();
        		fields[i-1].setAccessible(true);
        		fields[i-1].set(object, value);
        		fields[i-1].setAccessible(flag);
        	}
        }   
		return (T) object;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}

package com.xc.yideng.DBUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListBean<T> {

	private Class<?> clazz;
	
	public ListBean(Class<?> clazz){
		this.clazz = clazz;
	}
	/**
	 * 封装结果集(多个)
	 * @param rs
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> beanList(ResultSet rs,Class<?> clazz) throws Exception{
        List<T> list = new ArrayList<>();
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
        	list.add((T) object);
        }   
		return list;
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}

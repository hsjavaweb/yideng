package com.xc.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xc.domain.MyAnnotation;

public class DataHandler {

	/**
	 * 数据处理
	 * @param type
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static List<Object> ListHandler(Class<?> type, ResultSet rs) throws Exception{
		Field[] fields = type.getDeclaredFields();
		Object object = type.newInstance();
		List<Object> list = new ArrayList<>();
		while (rs.next()) {
			for(int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);// 取得指定注释
				if(ma!=null) {
					Object value = rs.getObject(ma.name());
					if(String.class.isAssignableFrom(fields[i].getType())) {
						fields[i].set(object, (String)value);
						continue;
					}if(int.class.isAssignableFrom(fields[i].getType()) || 
							Integer.class.isAssignableFrom(fields[i].getType())) {
						if(value instanceof Integer) {
							fields[i].set(object,value);
						}else {
							fields[i].set(object, java.lang.Integer.parseInt((String)value));
						}
						continue;
					}if(Date.class.isAssignableFrom(fields[i].getType())) {
						fields[i].set(object, (Date)value);
						continue;
					}
				}
			}
			list.add(object);
		}
		return list;
	}
}

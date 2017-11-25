package com.xc.yideng;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xc.domain.MyAnnotation;
import com.xc.util.C3P0Util;
import com.xc.util.DataHandler;

public class DbutilImpl implements Dbutil{

	private Field[] fields = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static Connection conn = null;
	static {
		conn = C3P0Util.getConnection();
	}
	
	@Override
	public Object getByKey(Class<?> type, int id){
		try {
			StringBuilder sql =  new StringBuilder("select ");//"select name,password, from "+type.getSimpleName()+" where id=?";
			fields = type.getDeclaredFields();
			String name = null;
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);// 取得指定注释
	            //判断注解是否为空，如果不为空循环
				if(ma != null) {
					sql.append(ma.name());
					if( i < fields.length - 1 ){
						sql.append(",");
					}
					//记录主键字段
					if("key".equals(ma.primarykey())){
						name = ma.name();
					}
				}
			}
			//有主键
			if(name!=null){
				sql.append(" from "+type.getSimpleName()+" where "+ name +"=?");
			}else {
				//如果没有，得到第一个字段名
				name = fields[0].getAnnotation(MyAnnotation.class).name();
				sql.append(" from "+type.getSimpleName()+" where "+ name +"=?");
			}
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			//数据封装
			List<Object> list = DataHandler.ListHandler(type, rs);
			//判断防止发生索引越界异常
			if(list.size() == 1) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("查询失败");
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
	}

	@Override
	public boolean insert(Object type) {
		try {
			//此处为主键自增
			Class<?> clazz = type.getClass();
			StringBuilder sql = new StringBuilder("INSERT INTO "+clazz.getSimpleName()+"(");
			StringBuilder sql1 = new StringBuilder();
			fields = clazz.getDeclaredFields();
			//拼装sql语句
			for(int i = 0; i < fields.length; i++){
				MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);
				sql.append(ma.name()); //INSERT INTO student(id,name,password) values(?,?,?);
				sql1.append("?");
				if(i<fields.length-1){
					sql.append(",");
					sql1.append(",");
				}
			}
			sql.append(") values(").append(sql1).append(");");
			ps = conn.prepareStatement(sql.toString());
			//给问号赋值
			for(int i = 0; i < fields.length; i++){
				fields[i].setAccessible(true);
				ps.setObject(i+1, fields[i].get(type));
			}
			boolean b = ps.execute();
			return !b;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public boolean delByKey(Class<?> type, Integer id) {
		fields = type.getDeclaredFields();
		String sql = null;
		try {
			for (Field field : fields) {
				MyAnnotation ma = field.getAnnotation(MyAnnotation.class);
				if("key".equals(ma.primarykey())) {
					sql = "DELETE FROM "+type.getSimpleName()+" WHERE "+ma.name()+"=?";
					break;
				}
			}
			//如果没有主键去第一个
			if(sql==null) {
				sql = "DELETE FROM "+type.getSimpleName()+" WHERE "+fields[0].getAnnotation(MyAnnotation.class).name()+"=?";
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return !ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public boolean update(Object type) {
		//根据id更改数据
		//UPDATE student SET NAME='哈哈' WHERE id=9
		Field field = null;
		String str = null;
		List<Object> list = new ArrayList<>();
		try {
			Class<?> clazz = type.getClass();
			//开始拼装sql语句
			StringBuilder sql = new StringBuilder("UPDATE "+clazz.getSimpleName()+" SET ");
			fields = clazz.getDeclaredFields();
			for(int i = 0; i < fields.length; i++){
				fields[i].setAccessible(true);
				Object object = fields[i].get(type);	//得到成员变量的值，值为null不设置
				if(object!=null){
					MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);
					//判断是否为主键id
					if("key".equals(ma.primarykey())) {		//找出主键字段，并记录
						str = "where "+ma.name()+"=?";
						field = fields[i];
 						continue;
					}
					list.add(object);
					sql.append(ma.name()+"=?");	//成员变量的值不为null就更改
				}if(i<fields.length-1){
					sql.append(",");
				}
			}
			sql.append(str);
			ps = conn.prepareStatement(sql.toString());
			for(int i = 0; list!=null && i < list.size(); i++){
				ps.setObject(i+1, list.get(i));
			}
			if(field!=null && list!=null) {
				ps.setObject(list.size()+1, field.get(type));
			}
			int i = ps.executeUpdate();
			return i == 0 ? false : true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public List<Object> getAll(Class<?> type) {
		try {
			StringBuilder sql =  new StringBuilder("select ");
			fields = type.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);// 取得指定注释
	            //判断注解是否为空，如果不为空循环
				if(ma != null) {
					sql.append(ma.name());
					if( i < fields.length - 1 ){
						sql.append(",");
					}
				}
			}
			sql.append(" from "+type.getSimpleName());
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			return DataHandler.ListHandler(type, rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public List<Object> get(Class<?> type, String str, Object... obj) {

		try {
			StringBuilder sql =  new StringBuilder("select ");
			fields = type.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				MyAnnotation ma = fields[i].getAnnotation(MyAnnotation.class);// 取得指定注释
	            //判断注解是否为空，如果不为空循环
				if(ma != null) {
					sql.append(ma.name());
					if( i < fields.length - 1 ){
						sql.append(",");
					}
				}
			}
			sql.append(" from "+type.getSimpleName()+" where "+str);
			ps = conn.prepareStatement(sql.toString());
			for(int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
			ResultSet rs = ps.executeQuery();
			
			return DataHandler.ListHandler(type, rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}
}


package com.xc.yideng;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xc.util.C3P0Util;

public class DbutilImpl implements Dbutil{

	private Connection conn;
	public DbutilImpl(Connection conn){
		this.conn = conn;
	}
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public Object getByKey(Class<?> type, int id){
		try {
			String sql ="select * from "+type.getSimpleName()+" where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Field[] fields = type.getDeclaredFields();
			Object object = type.newInstance();
			while (rs.next()) {
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = rs.getObject(field.getName());
					field.set(object, value);
				}
			}
			return object;
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
			StringBuilder sb = new StringBuilder("INSERT INTO "+clazz.getSimpleName()+"(");
			String str = "";
			Field[] fields = clazz.getDeclaredFields();
			//拼装sql语句
			for(int i = 0; i < fields.length; i++){
				sb.append(fields[i].getName());
				str+="?";
				if(i<fields.length-1){
					sb.append(",");
					str+=",";
				}
			}
			sb.append(") values("+str+");");
			ps = conn.prepareStatement(sb.toString());
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
		try {
			String sql = "DELETE FROM "+type.getSimpleName()+" WHERE "+type.getDeclaredFields()[0].getName()+"=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			boolean b = ps.execute();
			return !b;
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
		try {
			Class<?> clazz = type.getClass();
			String sql = "UPDATE "+clazz.getSimpleName()+" SET";
			Field[] fields = clazz.getDeclaredFields();
			for(int i = 1; i < fields.length; i++){
				fields[i].setAccessible(true);
				Object object = fields[i].get(type);
				if(object!=null){
					sql+=" "+fields[i].getName()+"='"+fields[i].get(type)+"'";
				}if(i<fields.length-1){
					sql+=",";
				}
			}
			fields[0].setAccessible(true);
			sql+=" where "+fields[0].getName()+"="+fields[0].get(type);
			
			Statement stemt = conn.createStatement();
			int i = stemt.executeUpdate(sql);
			if(i!=0){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public List<Object> getAll(Class<?> type) {
		try {
			String sql = "select * from "+type.getSimpleName();
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Field[] fields = type.getDeclaredFields();
			List<Object> list = new ArrayList<Object>();
			Object object = null;
			while (rs.next()) {
				object = type.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = rs.getObject(field.getName());
					field.set(object, value);
				}
				list.add(object);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public void saveOrUpdate(Object type) {
		try {
			Class<?> clazz = type.getClass();
			Field[] fields = clazz.getDeclaredFields();
			fields[0].setAccessible(true);
			System.out.println(fields[0].get(type));
			//判断id是否有值，没有值保存，有值更新
			if(fields[0].get(type)=="0" || fields[0].get(type)==null){
				StringBuilder sb = new StringBuilder("INSERT INTO "+clazz.getSimpleName()+"(");
				String str = "";
				for (int i = 0; i < fields.length; i++) {
					//拼装sql语句
					sb.append(fields[i].getName());
					str+="?";
					if(i<fields.length-1){
						sb.append(",");
						str+=",";
					}
				}
				sb.append(") values("+str+");");
				System.out.println(sb.toString());
				ps = conn.prepareStatement(sb.toString());
				for(int i = 0; i < fields.length; i++){
					fields[i].setAccessible(true);
					ps.setObject(i+1, fields[i].get(type));
				}
			}else{
				String sql = "UPDATE "+clazz.getSimpleName()+" SET";
				for(int i = 1; i < fields.length; i++){
					fields[i].setAccessible(true);
					Object object = fields[i].get(type);
					if(object!=null){
						sql+=" "+fields[i].getName()+"='"+fields[i].get(type)+"'";
					}if(i<fields.length-1){
						sql+=",";
					}
				}
				fields[0].setAccessible(true);
				sql+=" where "+fields[0].getName()+"=?";
				ps = conn.prepareStatement(sql);
				fields[0].setAccessible(true);
				ps.setObject(1, fields[0].get(type));
			}
			ps.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public List<Object> get(Class<?> type, String sql, Object... obj) {

		try {
			PreparedStatement ps = conn.prepareStatement("select * from "+type.getSimpleName()+" where "+sql);
			for(int i = 0; i < obj.length; i++){
				ps.setObject(i+1, obj[i]);
			}
			ResultSet rs = ps.executeQuery();
			Field[] fields = type.getDeclaredFields();
			List<Object> list = new ArrayList<Object>();
			Object object = null;
			while(rs.next()){
				while (rs.next()) {
					object = type.newInstance();
					for (Field field : fields) {
						field.setAccessible(true);
						Object value = rs.getObject(field.getName());
						field.set(object, value);
					}
					list.add(object);
				}
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}
}

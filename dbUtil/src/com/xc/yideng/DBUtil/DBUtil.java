package com.xc.yideng.DBUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUtil {

	private Connection conn = null;

	public DBUtil(){
		super();
	}
	public DBUtil(Connection conn){
		this.conn  = conn;
	}
	/**
	 * 根据类名创建表
	 * @param clazz
	 * @throws Exception
	 */
	public int createTable(Class<?> clazz) throws Exception{
		
		String clazzName = clazz.getSimpleName();
		StringBuffer sb = new StringBuffer();
		sb.append("create table "+clazzName+" (");
		//获得所有字段
		Field[] fields = clazz.getDeclaredFields(); 
		for (int i = 0; i < fields.length; i++) {
		    fields[i].setAccessible(true);
		    String fieldName = fields[i].getName();
		  
		    //获取字段类型格式：class java.lang.String
		    String classType = fields[i].getType().toString(); 
		  
		    //进行截取
		    int lastIndex = classType.lastIndexOf(".");
	        classType = classType.substring(lastIndex + 1);
	       
	        //拼装sql语句
	        if("int".equals(classType)){
	        	sb.append(fieldName+" int");
	        }else if("String".equals(classType)){
	        	sb.append(fieldName+" varchar(20)");
	        }else if("Date".equals(classType)){
	        	sb.append(fieldName+" Date");
	        }if(i < fields.length-1){
	        	sb.append(",");
	        }
		}  
		sb.append(");");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询所有
	 * @param lb
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public <T> List<T> findAll(ListBean<T> lb) throws SQLException, Exception{
		Class<?> clazz = lb.getClazz();
		String simpleName = clazz.getSimpleName();
		PreparedStatement ps = conn.prepareStatement("select * from "+simpleName);
		ResultSet rs = ps.executeQuery();
		return (List<T>)lb.beanList(rs, clazz);
	}
	
	/**
	 * 条件查找结果为多个条件
	 * @param sql
	 * @param lb
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public <T> List<T> find(String sql, ListBean<T> lb,Object...obj) throws SQLException, Exception{
		Class<?> clazz = lb.getClazz();
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < obj.length; i++){
			ps.setObject(i+1, obj[i]);
		}
		ResultSet rs = ps.executeQuery();
		return lb.beanList(rs, clazz);
	}
	/**
	 * 条件查找结果为一个条件
	 * @param sql
	 * @param objectBean
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T> T find(String sql, ObjectBean<T> ob, Object...obj) throws Exception {
		
		Class<?> clazz = ob.getClazz();
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < obj.length; i++){
			ps.setObject(i+1, obj[i]);
		}
		ResultSet rs = ps.executeQuery();
		return ob.beanList(rs, clazz);
	}
	/**
	 * 根据条件更改
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql, Object...obj) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < obj.length; i++){
			ps.setObject(i+1, obj[i]);
		}
		return ps.executeUpdate();
	}
	/**
	 * 增加数据操作
	 * @param sql
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int insert(String sql, Object...obj) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < obj.length; i++){
			ps.setObject(i+1, obj[i]);
		}
		return ps.executeUpdate();
	}
	
	/**
	 * 删除语句
	 * @throws SQLException 
	 */
	public int delete(String sql, Object...obj) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < obj.length; i++){
			ps.setObject(i+1, obj[i]);
		}
		return ps.executeUpdate();
	}
}

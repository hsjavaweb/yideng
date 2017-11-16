package com.xc.yideng;

import java.util.List;

public interface Dbutil {

	/**
	 * 根据id
	 * 查询
	 * @param type
	 * @param id
	 * @return
	 */
	public Object getByKey(Class<?> type, int id);
	
	/**
	 * 插入
	 * @param type
	 * @return
	 */
	public boolean insert(Object type);
	
	/**
	 * 删除
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean delByKey(Class<?> type, Integer id);
	
	/**
	 * 更新
	 * @param type
	 * @return
	 */
	public boolean update(Object type);
	
	/**
	 * 查询所有
	 * @param type
	 * @return
	 */
	public List<Object> getAll(Class<?> type);
	
	/**
	 * 保存或更新
	 * @param type
	 */
	public void saveOrUpdate(Object type);
	
	/**
	 * 根据其他条件查找
	 * @param type
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List<Object> get(Class<?> type, String sql, Object...obj);
}

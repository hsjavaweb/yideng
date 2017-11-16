package com.xc.yideng;

import java.util.List;

public interface Dbutil {

	/**
	 * ����id
	 * ��ѯ
	 * @param type
	 * @param id
	 * @return
	 */
	public Object getByKey(Class<?> type, int id);
	
	/**
	 * ����
	 * @param type
	 * @return
	 */
	public boolean insert(Object type);
	
	/**
	 * ɾ��
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean delByKey(Class<?> type, Integer id);
	
	/**
	 * ����
	 * @param type
	 * @return
	 */
	public boolean update(Object type);
	
	/**
	 * ��ѯ����
	 * @param type
	 * @return
	 */
	public List<Object> getAll(Class<?> type);
	
	/**
	 * ��������
	 * @param type
	 */
	public void saveOrUpdate(Object type);
	
	/**
	 * ����������������
	 * @param type
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List<Object> get(Class<?> type, String sql, Object...obj);
}

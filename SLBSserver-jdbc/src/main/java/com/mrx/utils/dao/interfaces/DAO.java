package com.mrx.utils.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	/**
	 * 把对象保存到数据库
	 * 
	 * @param entity
	 * 			要保存的对象
	 * @throws SQLException
	 */
	public void save(T entity) throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	/***
	 * 批量保存
	 * @param list
	 * 			对象的list
	 * @throws SQLException
	 */
	public void saveAll(List<T> list) throws SQLException;
	
	/**
	 * 通过主键修改数据
	 * @param entity
	 * 			需要修改的类
	 * @throws SQLException
	 */
	public void merge(T entity)throws SQLException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 *  执行sql语句
	 * @param sql
	 * 		执行的sql语句
	 * @param params
	 * 		参数
	 * @return
	 * 		是否执行成功
	 * @throws SQLException
	 */
	
	public void merge(String sql, String... params) throws SQLException;
	
	/**
	 * 通过主键删除数据
	 * @param entity
	 * 		需要删除的数据
	 * @throws SQLException
	 */
	public void remove(T entity) throws SQLException;
	
	/**
	 * 查询
	 * @param sql
	 *			查询的sql语句 		
	 * @param params
	 * 			条件
	 * @return
	 * 			返回对象
	 * @throws SQLException
	 */
	
	public T getEntity(String sql ,String... params);
	
	/**
	 * 查询
	 * @param sql
	 *			查询的sql语句 		
	 * @param params
	 * 			条件
	 * @return
	 * 			返回对象
	 * @throws SQLException
	 */
	
	public List<T> getList(String sql ,String... params);
	
	/**
	 * 查询
	 * @param sql
	 *			查询的sql语句 		
	 * @return
	 * 			返回对象
	 * @throws SQLException
	 */
	
	public T find(String sql);
	
	/**
	 * 批量查询
	 * 
	 * @param sql
	 * 			sql语句
	 * @return
	 * 		返回对象
	 * @throws SQLException
	 */
	public List<T> findAll(String sql);
	
	
}

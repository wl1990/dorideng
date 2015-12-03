package com.bxs.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.bxs.server.model.BasePojo;





public interface BaseDao<T extends BasePojo,PK extends Serializable> {
	/**
	 * 通过反射，初始化范型
	 */
	void initModel();
	
	boolean addEntity(T pojo);
	boolean updateEntity(T pojo);
	boolean deleteEntity(T pojo);
	T getById(Class clazz,int id);
	T getById(int id);
	/**
	 * 查询单表分页的数据
	 * @param clazz 对象的class
	 * @param currentPage 查询的当前页
	 * @param size 查询的每页数据
	 * @return
	 */
	List<? extends Object> getPerPageList(Class clazz,int currentPage, int size);		
	
	/**
	 * 查询单表最大的页数
	 * @param clazz
	 * @param size 每页数据
	 * @return
	 */
	int getMaxPage(Class clazz,int size);
	/**
	 * 查询单表最大的页数
	 * @param clazz
	 * @param size 每页数据
	 * @return
	 */
	int getMaxPage(int size);
	/**
	 * 查询单表最大的页数
	 * @param clazz
	 * @param size 每页数据
	 * @return
	 */
	int getMaxPage(Session session,int size);
	/**
	 * 查询单表记录总数
	 * @param clazz
	 * @return
	 */
	int getAmount(Class clazz);
	
	/**
	 * 查询单表的全部数据记录
	 * @param clazz
	 * @return
	 */
	List<? extends Object> getAllRecord(Class clazz);
	
	/**
	 * 查询单表的全部数据记录
	 */
	
	List<T> getAll();
}

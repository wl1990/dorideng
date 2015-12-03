package com.bxs.server.manager;

import java.io.Serializable;
import java.util.List;

import com.bxs.server.model.BasePojo;



public interface BaseManager<T extends BasePojo,PK extends Serializable> {
	boolean dbAdd(T t);
	boolean dbSave(T t);
	boolean dbUpdate(T t);
	T getById(Class clazz,int id);
	T getById(int id);
	/**
	 * 删除
	 * @param t
	 * @return
	 */
	boolean dbDelete(T t); 
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	boolean dbDelete(List<T> list);
	
	/**
	 * 单表分页查询
	 * @param clazz 对象
	 * @param currentPage 当前页
	 * @param size 每页的数据
	 * @return
	 */
	List<? extends Object> getMerPerPage(Class clazz,int currentPage,int size);
	/**
	 *单表最大页数
	 * @param size
	 * @param clazz
	 * @return
	 */
	int getMaxPage(int size,Class clazz);
	
	/**
	 * 单表记录总数
	 * @param clazz
	 * @return
	 */
	int getAmount(Class clazz);
	
	/**
	 * 所有记录
	 */
	List<T> getAll();
}

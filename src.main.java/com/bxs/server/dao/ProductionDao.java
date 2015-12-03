package com.bxs.server.dao;

import java.util.List;

import com.bxs.server.model.Production;


public interface ProductionDao {
	
	
	List<Production> getByParentId(String parent_id);
	
	boolean deleteBySelfId(String nodeid);
	/**
	 * 根据selfid查询产品
	 * @param nodeid
	 * @return
	 */
	Production getBySelfId(String nodeid);
	
	/**
	 * 根据产品名字查询
	 * @param proName
	 * @return
	 */
	List<Object[]> getByProcname(String proName);

}

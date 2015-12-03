package com.bxs.server.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxs.server.dao.ProductionDao;
import com.bxs.server.dao.impl.ProductionDaoImpl;
import com.bxs.server.manager.ProductionManager;
import com.bxs.server.model.Production;
import com.bxs.server.model.RightMenu;

@Service
public class ProductionManagerImpl extends BaseManagerImpl<Production,String> implements ProductionManager{
	@Autowired
	private ProductionDao productionDao;
	@Autowired
	public ProductionManagerImpl(ProductionDaoImpl productionDao){
		thisDao=productionDao;
	}
	/**
	 * 查询父类的所有子产品
	 * @param parent_id
	 * @return
	 */
	public List<Production> getByParentId(String parent_id) {
		return productionDao.getByParentId(parent_id);
	}
	
	/**
	 * 删除产品
	 * @param nodeid
	 */
	public boolean deleteBySelfId(String nodeid) {
		return productionDao.deleteBySelfId(nodeid);
	}
	/**
	 * 查询产品
	 * @param nodeid
	 * @return
	 */
	public Production getBySelfId(String nodeid) {
		return productionDao.getBySelfId(nodeid);
	}
	/**
	 * 根据产品名查询
	 * @param proName
	 * @return
	 */
	public List<Object[]> getByProcname(String proName) {
		return productionDao.getByProcname(proName);
	}
	
	
}

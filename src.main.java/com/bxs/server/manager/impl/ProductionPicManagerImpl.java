package com.bxs.server.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxs.server.dao.ProductionPicDao;
import com.bxs.server.dao.impl.ProductionPicDaoImpl;
import com.bxs.server.manager.ProductionPicManager;
import com.bxs.server.model.ProductionPictures;

@Service
public class ProductionPicManagerImpl extends BaseManagerImpl<ProductionPictures,String> implements ProductionPicManager{
	@Autowired
	private  ProductionPicDao productionPicDao;
	@Autowired
	private ProductionPicManagerImpl(ProductionPicDaoImpl productionPicDao){
		thisDao=productionPicDao;
	}
	/**
	 * 根据图片id,status查询
	 * @param status
	 * @param parseInt
	 * @return
	 */
	public List<ProductionPictures> getByProId(int proid, int status) {
		return productionPicDao.getByProId(proid,status);
	}
	
	/**
	 * 根据图片id查询
	 * @param proid
	 * @return
	 */
	public List<ProductionPictures> getByProId(int proid) {
		return productionPicDao.getByProId(proid);
	}
	
	
	
	
}

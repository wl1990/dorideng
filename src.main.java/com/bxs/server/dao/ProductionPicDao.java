package com.bxs.server.dao;

import java.util.List;

import com.bxs.server.model.ProductionPictures;

public interface ProductionPicDao {
	
	/**
	 * 根据图片id,status查询
	 * @param status 
	 * @param parseInt
	 * @return
	 */
	List<ProductionPictures> getByProId(int proid, int status);
	
	/**
	 * 根据图片id查询
	 * @param proid
	 * @return
	 */
	List<ProductionPictures> getByProId(int proid);

}

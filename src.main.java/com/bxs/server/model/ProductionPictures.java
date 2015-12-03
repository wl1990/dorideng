package com.bxs.server.model;

import java.io.Serializable;

/**
 * 产品图片
 * @author fengyang04
 *
 */
public class ProductionPictures extends BasePojo<Integer>{
	private Integer productionId; //图片id
	private String picUrl; //图片url
	private Integer status; //递增,区分旧图片和新图片 旧图片status小
	private String createTime; //创建时间
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}

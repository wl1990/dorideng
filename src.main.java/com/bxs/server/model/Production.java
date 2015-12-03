package com.bxs.server.model;

/**
 * 产品
 * @author fengyang04
 *
 */
public class Production extends BasePojo<Integer>{
	private String proName; //产品名字
	private String proPic; // 产品图片
	private Integer proType; //产品类型
	private String proselfId; //产品自己id
	private String proparentId; //父类产品id
	private String detail;  //产品详情
	
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public Integer getProType() {
		return proType;
	}
	public void setProType(Integer proType) {
		this.proType = proType;
	}
	public String getProselfId() {
		return proselfId;
	}
	public void setProselfId(String proselfId) {
		this.proselfId = proselfId;
	}
	public String getProparentId() {
		return proparentId;
	}
	public void setProparentId(String proparentId) {
		this.proparentId = proparentId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
}

package com.bxs.server.model;

public class RecoverPwd extends BasePojo<Integer>{
	private String email;
	private String identifycode;
	private String registerDate;
	private Integer isvalid;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentifycode() {
		return identifycode;
	}
	public void setIdentifycode(String identifycode) {
		this.identifycode = identifycode;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}
	
	
	
}

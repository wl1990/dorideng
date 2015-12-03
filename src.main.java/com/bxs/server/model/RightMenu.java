package com.bxs.server.model;

/**
 * 权限菜单
 * @author Administrator
 *
 */
public class RightMenu extends BasePojo<Integer>{
	private String selfId;    //自身id
	private String parentId;  //父id
	private String menuName;  //菜单名称
	private String menuUrl;   //菜单url
	private String imageUrl;  //菜单图片
	private String menuType;  //菜单类型
	private String menuMark;  //菜单标记
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getSelfId() {
		return selfId;
	}
	public void setSelfId(String selfId) {
		this.selfId = selfId;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuMark() {
		return menuMark;
	}
	public void setMenuMark(String menuMark) {
		this.menuMark = menuMark;
	}
	
}

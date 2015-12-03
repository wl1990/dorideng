package com.bxs.server.dao;

import java.util.List;

import com.bxs.server.model.RightMenu;

public interface RightMenuDao {
	public List<RightMenu> getAll();
	public List<RightMenu> getByParentId(String id);
	public void deleteBySelfId(String nodeid);
	public RightMenu getBySelfId(String nodeid);
	
}

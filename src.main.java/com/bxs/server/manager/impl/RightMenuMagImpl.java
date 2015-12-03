package com.bxs.server.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxs.server.dao.RightMenuDao;
import com.bxs.server.dao.impl.RightMenuDaoImpl;
import com.bxs.server.model.RightMenu;

@Service
public class RightMenuMagImpl extends BaseManagerImpl<RightMenu,String>{
	@Autowired
	private RightMenuDao rmenudao;
	
	@Autowired
	public RightMenuMagImpl(RightMenuDaoImpl rmenudao){
		thisDao=rmenudao;
	}
	
	/**
	 * 所有记录
	 */
	public List<RightMenu> getAll(){
		return rmenudao.getAll();
	}
	
	/**
	 * 查找父节点的所有子节点
	 * @param id
	 * @return
	 */
	public List<RightMenu> getByParentId(String id) {
		return rmenudao.getByParentId(id);
	}
	
	/**
	 * 删除节点及所有子节点
	 * @param nodeid
	 */
	public void deleteBySelfId(String nodeid) {
		rmenudao.deleteBySelfId(nodeid);
	}
	
	/**
	 * 查找节点
	 * @param nodeid
	 * @return
	 */
	public RightMenu getBySelfId(String nodeid) {
		return rmenudao.getBySelfId(nodeid);
	}
	
}

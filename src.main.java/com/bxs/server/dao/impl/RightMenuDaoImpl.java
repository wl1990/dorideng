package com.bxs.server.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bxs.server.dao.RightMenuDao;
import com.bxs.server.model.RightMenu;
import com.bxs.server.util.HibernateUtil;

@Component
public class RightMenuDaoImpl extends BaseDaoImpl<RightMenu,String> implements RightMenuDao{
	/**
	 * 查询所有
	 */
	@Override
	public List<RightMenu> getAll() {
		try{
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from RightMenu");
		return query.list();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * 父节点的所有子节点
	 * @param id
	 * @return
	 */
	@Override
	public List<RightMenu> getByParentId(String id) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="";
		if(id==null)
			sql=" from RightMenu rm where rm.parentId is null";
		else
			sql="from RightMenu rm where rm.parentId=?";
		Query query=session.createQuery(sql);
		if(id!=null)
			query.setString(0, id);
		return query.list();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	/*
	 * 删除节点及所有子节点
	 */
	@Override
	public void deleteBySelfId(String nodeid) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="delete from RightMenu rm where rm.selfId=?";
		session.beginTransaction();
		Query query=session.createQuery(sql);
		query.setString(0, nodeid);
		query.executeUpdate();
		session.getTransaction().commit();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public RightMenu getBySelfId(String nodeid) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="from RightMenu rm where rm.selfId=?";
		Query query=session.createQuery(sql);
		query.setString(0, nodeid);
		List<RightMenu> list=query.list();
		if(list!=null)
			return list.get(0);
		else
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	
}

package com.bxs.server.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bxs.server.dao.ProductionDao;
import com.bxs.server.model.Production;
import com.bxs.server.util.HibernateUtil;

@Component
public class ProductionDaoImpl extends BaseDaoImpl<Production,String> implements ProductionDao{
	private static final String DELETE_BY_SELFID="delete from Production proc where proc.proselfId=?";
	private static final String QUERY_BY_SELFID="from Production pd where pd.proselfId=?";
	private static final String QUERY_BY_PROCNAME="select id,pro_name from production where pro_name=?";
	@Override
	public List<Production> getByParentId(String parent_id) {
		try{
			Session session=HibernateUtil.currentSession();
			String sql="";
			if(parent_id==null)
				sql=" from Production pd where pd.proparentId is null";
			else
				sql="from Production pd where pd.proparentId=?";
			Query query=session.createQuery(sql);
			if(parent_id!=null)
				query.setString(0, parent_id);
			return query.list();
			}finally{
				HibernateUtil.closeSession();
			}
	}

	@Override
	public boolean deleteBySelfId(String nodeid) {
		try{
			Session session=HibernateUtil.currentSession();
			session.beginTransaction();
			Query query=session.createQuery(DELETE_BY_SELFID);
			query.setString(0, nodeid);
			query.executeUpdate();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally{
				HibernateUtil.closeSession();
			}
		return true;
	}

	@Override
	public Production getBySelfId(String nodeid) {
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery(QUERY_BY_SELFID);
			query.setString(0, nodeid);
			List<Production> plist=query.list();
			if(plist!=null)
				return plist.get(0);
			}finally{
				HibernateUtil.closeSession();
			}
		return null;
	}

	@Override
	public List<Object[]> getByProcname(String proName) {
		try{
			Session session=HibernateUtil.currentSession();
			SQLQuery query=session.createSQLQuery(QUERY_BY_PROCNAME);
			query.setString(0, proName);
			List<Object[]> plist=query.list();
			if(plist!=null)
				return plist;
			}finally{
				HibernateUtil.closeSession();
			}
		return null;
	}
	
	

	
}

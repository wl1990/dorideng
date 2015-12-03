package com.bxs.server.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bxs.server.dao.ProductionPicDao;
import com.bxs.server.model.ProductionPictures;
import com.bxs.server.util.HibernateUtil;

@Component
public class ProductionPicDaoImpl extends BaseDaoImpl<ProductionPictures,String> implements ProductionPicDao{
	private static final String QUERY_BY_PROC_ID_STATUS="from ProductionPictures where productionId=? and status=?";
	private static final String QUERY_BY_PROC_ID="from ProductionPictures where productionId=?";
	
	@Override
	public List<ProductionPictures> getByProId(int proid,int status) {
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery(QUERY_BY_PROC_ID_STATUS);
			query.setInteger(0, proid);
			query.setInteger(1, status);
			return query.list();
			}finally{
				HibernateUtil.closeSession();
			}
	}
	@Override
	public List<ProductionPictures> getByProId(int proid) {
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery(QUERY_BY_PROC_ID);
			query.setInteger(0, proid);
			return query.list();
			}finally{
				HibernateUtil.closeSession();
			}
	}
	
}

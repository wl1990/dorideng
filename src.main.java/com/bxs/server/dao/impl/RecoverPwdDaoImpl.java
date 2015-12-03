package com.bxs.server.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bxs.server.dao.RecoverPwdDao;
import com.bxs.server.model.RecoverPwd;
import com.bxs.server.util.HibernateUtil;


@Component
public class RecoverPwdDaoImpl extends BaseDaoImpl<RecoverPwd,String> implements RecoverPwdDao{
	private static final String QUERY_BY_USERNAME_STATUS="from RecoverPwd  where email=? and isvalid=?";
	private static final String QUERY_BY_IDENTITY_STATUS="from RecoverPwd  where identifycode=? and isvalid=?";
	
	@Override
	public List<RecoverPwd> getByUsernameAndStatus(String username, int status) {
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery(QUERY_BY_USERNAME_STATUS);
			query.setString(0, username);
			query.setInteger(1, status);
			List<RecoverPwd> lists=query.list();
			if(lists!=null && lists.size()>0)
				return lists;
			}finally{
				HibernateUtil.closeSession();
			}
		return null;
	}

	@Override
	public RecoverPwd getByIdentityStatus(String identity, int status) {
		try{
			Session session=HibernateUtil.currentSession();
			Query query=session.createQuery(QUERY_BY_IDENTITY_STATUS);
			query.setString(0, identity);
			query.setInteger(1, status);
			List<RecoverPwd> lists=query.list();
			if(lists!=null && lists.size()>0)
				return lists.get(0);
			}finally{
				HibernateUtil.closeSession();
			}
		return null;
	}
	
}

package com.bxs.server.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/




import com.bxs.server.dao.BaseDao;
import com.bxs.server.model.BasePojo;
import com.bxs.server.util.HibernateUtil;
import com.bxs.server.util.ReflectionUtils;



public  class BaseDaoImpl<T extends BasePojo,PK extends Serializable> implements BaseDao<T,PK>{
	protected T model;
	private static final Logger log=Logger.getLogger(BaseDaoImpl.class);
	public BaseDaoImpl(){
		initModel();
	}
	@Override
	public void initModel() {
		if(model==null){
			Class thisEntityclass = ReflectionUtils.getSuperClassGenricType(getClass());
			try {
				model = (T) thisEntityclass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean addEntity(T pojo) {
		boolean breakoff = false;
		try{
	/*	if(pojo.getId()==null || pojo.getId().toString().isEmpty())
		pojo.setId(UUIDUtil.createUUID());*/
			HibernateUtil.currentSession().beginTransaction();
			HibernateUtil.currentSession().clear();
			HibernateUtil.currentSession().save(pojo);
			HibernateUtil.currentSession().getTransaction().commit();
		}catch(Exception ex){
			breakoff=true;
			ex.printStackTrace();
			log.error("insert exception---"+ex.getMessage());
		}finally{
			if(breakoff)
			HibernateUtil.currentSession().getTransaction().rollback();
			HibernateUtil.closeSession();
		}
		return !breakoff;
	}

	@Override
	public boolean updateEntity(T pojo) {
		boolean breakoff = false;
		try{
		HibernateUtil.currentSession().beginTransaction();
		HibernateUtil.currentSession().clear();
		HibernateUtil.currentSession().update(pojo);
		HibernateUtil.currentSession().getTransaction().commit();
		
		}catch(Exception ex){
			breakoff=true;
			ex.printStackTrace();
			log.error("update exception---"+ex.getMessage());
		}finally{
			if(breakoff)
				HibernateUtil.currentSession().getTransaction().rollback();
			HibernateUtil.closeSession();
			
		}
		return !breakoff;
	}
	

	@Override
	public boolean deleteEntity(T pojo) {
		boolean breakoff = false;
		try{
			HibernateUtil.currentSession().beginTransaction();
			HibernateUtil.currentSession().delete(pojo);
			HibernateUtil.currentSession().getTransaction().commit();
			
		}catch(Exception ex){
			breakoff=true;
			ex.printStackTrace();
			log.error("delete exception---"+ex.getMessage());
		}finally{
			if(breakoff)
				HibernateUtil.currentSession().getTransaction().rollback();
			HibernateUtil.closeSession();
		}
		return !breakoff;
	}
	
	@Override
	public T getById(Class clazz,int id){
		try{
		Session session=HibernateUtil.currentSession();
		session.clear();
		String className=clazz.getSimpleName();
		String sql="from "+className+" where id=?";
		Query query=session.createQuery(sql);
		query.setInteger(0, id);
		List<T> list=query.list();
		if(list!=null &&list.size()>0)
		return list.get(0);
		return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<? extends Object> getPerPageList(Class clazz, int currentPage,
			int size) {
		try{
		Session session=HibernateUtil.currentSession();
		String className=clazz.getSimpleName();
		String sql="from "+className;
		Query query=session.createQuery(sql);
		if(currentPage<1)
			currentPage=1;
		int maxpage=getMaxPage(session,size);
		if(currentPage>maxpage)
			currentPage=maxpage;
		currentPage--;
		query.setFirstResult(currentPage*size);
		query.setMaxResults(size);
		List list=query.list();
		return list;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	@Override
	public int getMaxPage(Class clazz, int size) {
		try{
		
		Session session=HibernateUtil.currentSession();
		String sql="from "+clazz.getName();
		Query query=session.createQuery(sql);
		List<Object> list=query.list();
		if(list==null)
			return 1;
		else{
			int count=list.size();
			return (count%size)==0? (count/size):(count/size+1);
		}
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	@Override
	public int getMaxPage(Session session, int size) {
		String sql="from "+model.getClass().getSimpleName();
		Query query=session.createQuery(sql);
		List<Object> list=query.list();
		if(list==null)
			return 1;
		else{
			int count=list.size();
			return (count%size)==0? (count/size):(count/size+1);
		}
	}
	
	@Override
	public int getMaxPage(int size) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="from "+model.getClass().getSimpleName();
		Query query=session.createQuery(sql);
		List<Object> list=query.list();
		if(list==null)
			return 1;
		else{
			int count=list.size();
			return (count%size)==0? (count/size):(count/size+1);
		}
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public int getAmount(Class clazz) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="from "+clazz.getName();
		Query query=session.createQuery(sql);
		List<Object> list=query.list();
		if(list==null)
			return 0;
		else
			return list.size();
		}finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<? extends Object> getAllRecord(Class clazz) {
		try{
		Session session=HibernateUtil.currentSession();
		String sql="from "+clazz.getName();
		Query query=session.createQuery(sql);
		List<Object> list=query.list();
		return list;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public T getById(int id) {
		try{
		Session session=HibernateUtil.currentSession();
		session.clear();
		String className=model.getClass().getSimpleName();
		String sql="from "+className+" where id=?";
		Query query=session.createQuery(sql);
		query.setInteger(0, id);
		List<T> list=query.list();
		if(list!=null &&list.size()>0)
		return list.get(0);
		return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	@Override
	public List<T> getAll(){
		try{
		Session session=HibernateUtil.currentSession();
		session.clear();
		String className=model.getClass().getSimpleName();
		String sql="from "+className;
		Query query=session.createQuery(sql);
		List<T> list=query.list();
//		HibernateUtil.closeSession();
		if(list!=null &&list.size()>0)
		return list;
		return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
}

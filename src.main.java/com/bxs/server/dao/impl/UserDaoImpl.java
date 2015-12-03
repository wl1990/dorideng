package com.bxs.server.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bxs.server.dao.UserDao;
import com.bxs.server.model.User;
import com.bxs.server.util.HibernateUtil;


@Component
public class UserDaoImpl extends BaseDaoImpl<User,String> implements UserDao{
	
	/**
	 * 查询所有的信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<User> getList(){
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User");
		return query.list();
	}
	
	/**
	 * 根据用户id查询信息
	 * @param id
	 * @return
	 */
	@Override
	public User getById(String id) {
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User user where user.id=?");
		query.setString(0, id);
		List<User> list=query.list();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<User> getByUsernameAndPassword(String name, String password) {
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User user where user.userName=? and user.password=?");
		query.setString(0, name);
		query.setString(1, password);
		return query.list();
	}

	@Override
	public boolean isAlreadyExist(String loginName) {
		boolean flag=true;
		try{
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User user where user.userName=?");
		query.setString(0,loginName);
		List<User> list=query.list();
		if(list==null ||list.isEmpty()){
			flag=false;
		}
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	@Override
	public User getMemberByUserName(String loginName) {
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User user where user.userName=?");
		query.setString(0,loginName);
		List<User> list=query.list();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getMemberByEmail(String email) {
		try{
		Session session=HibernateUtil.currentSession();
		Query query=session.createQuery("from User user where user.email=?");
		query.setString(0,email);
		List<User> list=query.list();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}

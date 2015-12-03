package com.bxs.server.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxs.server.dao.UserDao;
import com.bxs.server.dao.impl.UserDaoImpl;
import com.bxs.server.model.User;


/**
 * 用户数据处理
 * @author Administrator
 *
 */
@Service
public class UserManagerImpl extends BaseManagerImpl<User,String>{
	@Autowired
	private UserDao userdao;
	public UserManagerImpl(){
		super();
	}
	@Autowired
	public UserManagerImpl(UserDaoImpl userdao){
		thisDao=userdao;
	}
	public List<User> getAll() {
		try {
			return userdao.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public User getById(String id) {
		return userdao.getById(id);
	}
	
	/**
	 * 根据同户名和密码判断用户登陆
	 * @param name
	 * @param password
	 * @return
	 */
	public List<User> getByUsernameAndPassword(String name, String password) {
		return userdao.getByUsernameAndPassword(name,password);
	}
	/**
	 * 判断有无此用户
	 * @param loginName
	 * @return
	 */
	public boolean isAlreadyExist(String loginName) {
		return userdao.isAlreadyExist(loginName);
	}
	
	/**
	 * 根据用户名查找
	 * @param loginName
	 * @return
	 */
	public User getMemberByUserName(String loginName) {
		return userdao.getMemberByUserName(loginName);
	}
	
	/**
	 * 根据邮箱查找
	 * @param email
	 * @return
	 */
	public User getMemberByEmail(String email) {
		return userdao.getMemberByEmail(email);
	}
	
	
	
}

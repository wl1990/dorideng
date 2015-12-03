package com.bxs.server.dao;

import java.util.List;

import com.bxs.server.model.User;

public interface UserDao {
	public List<User> getList();
	public User getById(String id);
	public List<User> getByUsernameAndPassword(String name, String password);
	public boolean isAlreadyExist(String loginName);
	public User getMemberByUserName(String loginName);
	public User getMemberByEmail(String email);
	
}

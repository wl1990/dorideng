package com.bxs.server.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxs.server.dao.RecoverPwdDao;
import com.bxs.server.dao.impl.RecoverPwdDaoImpl;
import com.bxs.server.model.RecoverPwd;


@Service
public class RecoverPwdManagerImpl extends BaseManagerImpl<RecoverPwd,String>{
	@Autowired
	private RecoverPwdDao recoverPwdDao;
	@Autowired
	public RecoverPwdManagerImpl(RecoverPwdDaoImpl recoverPwdDao){
		thisDao=recoverPwdDao;
	}
	public List<RecoverPwd> getByUsernameAndStatus(String username, int status) {
		return recoverPwdDao.getByUsernameAndStatus(username,status);
	}
	public RecoverPwd getByIdentityStatus(String identity, int status) {
		return recoverPwdDao.getByIdentityStatus(identity,status);
	}
	
	
	
	
}

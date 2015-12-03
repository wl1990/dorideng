package com.bxs.server.dao;

import java.util.List;

import com.bxs.server.model.RecoverPwd;

public interface RecoverPwdDao {

	List<RecoverPwd> getByUsernameAndStatus(String username, int status);
	RecoverPwd getByIdentityStatus(String identity, int status);

}

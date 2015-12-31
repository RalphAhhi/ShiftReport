package com.fv.shiftreport.manager;

import org.apache.commons.codec.binary.Base64;

import com.fv.shiftreport.dao.UserDao;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;


public class LoginManagerImpl implements LoginManager {
	
	private UserDao userDao;
	
	public UserResponse getUserByCredential(UserRequest request) throws Exception{
		request.setPassword(new String(Base64.encodeBase64(request.getPassword().getBytes())));
		return userDao.fetchUserByCredential(request);
		
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}

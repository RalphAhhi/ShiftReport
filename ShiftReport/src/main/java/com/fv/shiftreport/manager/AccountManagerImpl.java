package com.fv.shiftreport.manager;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.fv.shiftreport.dao.UserDao;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.Util;


public class AccountManagerImpl implements AccountManager {
	
	private UserDao userDao;
	
	public UserResponse getUserByCredential(UserRequest request) throws Exception{
		request.setPassword(new String(Base64.encodeBase64(request.getPassword().getBytes())));
		return userDao.fetchUserByCredential(request);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void registerUser(UserRequest user)throws Exception {
		validateRegistration(user);
		user.setPassword(new String(Base64.encodeBase64(user.getPassword().getBytes())));
		user.setId(Util.getMacAddress()+DatabaseUtil.sqlGetLastId("User"));
     	userDao.saveUser(user);

	}
	
	public UserResponse getUserById(String id)throws Exception {
     	return userDao.fetchUserById(id);

	}
	
	private void validateRegistration(UserRequest user) throws SQLException, Exception{
		if(Util.isEmpty(user.getFirstName()) ||
				Util.isEmpty(user.getLastName()) ||
				Util.isEmpty(user.getUserName()) ||
				Util.isEmpty(user.getPassword())){
			throw new Exception("Some fields have missing data");
		}
		if(null != userDao.fetchUserByUsername(user)){
			throw new Exception("User Already Exist");
		}
	}

	public List<UserResponse> getAllUsers() throws SQLException {
		return userDao.fetchAllUsers();
	}

}

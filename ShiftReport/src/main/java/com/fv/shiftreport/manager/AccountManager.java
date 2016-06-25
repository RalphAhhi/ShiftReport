package com.fv.shiftreport.manager;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.dao.UserDao;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;

public interface AccountManager {
	
	public UserResponse getUserByCredential(UserRequest userReq)throws SQLException, Exception;
	public void registerUser(UserRequest user) throws Exception;
	public void setUserDao(UserDao userDao);
	public UserResponse getUserById(String id)throws Exception;
	public List<UserResponse> getAllUsers() throws SQLException;

}

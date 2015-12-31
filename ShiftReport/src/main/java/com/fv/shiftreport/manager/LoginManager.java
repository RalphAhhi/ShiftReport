package com.fv.shiftreport.manager;

import java.sql.SQLException;

import com.fv.shiftreport.dao.UserDao;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;

public interface LoginManager {
	
	public UserResponse getUserByCredential(UserRequest userReq)throws SQLException, Exception;
	public void setUserDao(UserDao userDao);

}

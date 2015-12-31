package com.fv.shiftreport.dao;

import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;

public interface UserDao {

	
	public UserResponse fetchUserByCredential(UserRequest request) throws Exception;
}

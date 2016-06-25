package com.fv.shiftreport.dao;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;

public interface UserDao {

	
	public UserResponse fetchUserByCredential(UserRequest request) throws Exception;

	public void saveUser(UserRequest user)throws Exception;

	public UserResponse fetchUserByUsername(UserRequest user) throws SQLException;

	public UserResponse fetchUserById(String id) throws SQLException;;
	
	public List<UserResponse> fetchAllUsers()  throws SQLException;;
}

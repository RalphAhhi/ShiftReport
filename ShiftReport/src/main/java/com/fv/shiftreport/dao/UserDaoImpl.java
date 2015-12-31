package com.fv.shiftreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.DatabaseUtil;

public class UserDaoImpl implements UserDao{

	public UserResponse fetchUserByCredential(UserRequest request) throws Exception {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,firstname,lastname,username,firstlogin from fv.user where userName = ? and password = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, request.getUserName());
			ps.setString(2, request.getPassword());
			return build( ps.executeQuery());
		}finally {
			if(null!=dbConnection){
				dbConnection.close();
			}
			if(null!=ps){
				ps.close();
			}
			
		}

	}
	
	private UserResponse build(ResultSet rs) throws SQLException{
		UserResponse response = null;
		if(rs.next()){
			response = new UserResponse();
			response.setId(rs.getInt("id"));
			response.setFirstName(rs.getString("firstname"));
			response.setLastName(rs.getString("lastname"));
			response.setUserName(rs.getString("username"));
		    response.setFirstLogin(rs.getInt("firstlogin")==1?true:false);
		}
		return response;
	}

}

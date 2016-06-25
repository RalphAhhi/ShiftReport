package com.fv.shiftreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.DatabaseUtil;

public class UserDaoImpl implements UserDao {

	public UserResponse fetchUserByCredential(UserRequest request) throws Exception {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try {
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,firstname,lastname,username,role from fv.user where userName = ? and password = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, request.getUserName());
			ps.setString(2, request.getPassword());
			return buildWithCheck(ps.executeQuery());
		} finally {
			if (null != dbConnection) {
				dbConnection.close();
			}
			if (null != ps) {
				ps.close();
			}

		}

	}
	
	private UserResponse buildWithCheck(ResultSet rs) throws SQLException{
		if(rs.next()){
			return build(rs);
		}
		return null;
	}

	private UserResponse build(ResultSet rs) throws SQLException {
		UserResponse response = new UserResponse();
		response.setId(rs.getString("id"));
		response.setFirstName(rs.getString("firstname"));
		response.setLastName(rs.getString("lastname"));
		response.setUserName(rs.getString("username"));
		response.setRoleId(rs.getInt("role"));

		return response;
	}

	public void saveUser(UserRequest user) throws Exception {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try {
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "insert into fv.user (firstname,lastname,username,password,role,createdDate,createdBy,updatedDate,updatedBy,id) values (?,?,?,?,?,?,?,?,?,?)";
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getRole().getId());
			ps.setTimestamp(6, user.getCreatedDate());
			ps.setString(7, user.getCreatedBy());
			ps.setTimestamp(8, user.getUpdatedDate());
			ps.setString(9, user.getUpdatedBy());
			ps.setString(10, user.getId());
			ps.execute();
			dbConnection.commit();
		} finally {
			if (null != dbConnection) {
				dbConnection.close();
			}
			if (null != ps) {
				ps.close();
			}

		}

	}

	public UserResponse fetchUserByUsername(UserRequest user) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try {
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,firstname,lastname,username,role from fv.user where userName = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			return buildWithCheck(ps.executeQuery());
		} finally {
			if (null != dbConnection) {
				dbConnection.close();
			}
			if (null != ps) {
				ps.close();
			}
		}

	}

	public UserResponse fetchUserById(String id) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try {
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,firstname,lastname,username,role from fv.user where id = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return build(rs);
			}
			return null;
		} finally {
			if (null != dbConnection) {
				dbConnection.close();
			}
			if (null != ps) {
				ps.close();
			}

		}

	}

	public List<UserResponse> fetchAllUsers() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try {
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,firstname,lastname,username,role from fv.user ";
			ps = dbConnection.prepareStatement(sql);
			return buildList(ps.executeQuery());
		} finally {
			if (null != dbConnection) {
				dbConnection.close();
			}
			if (null != ps) {
				ps.close();
			}

		}

	}

	private List<UserResponse> buildList(ResultSet rs) throws SQLException {
		List<UserResponse> user = new ArrayList<UserResponse>();
		while (rs.next()) {
			user.add(build(rs));
		}
		return user;
	}

}

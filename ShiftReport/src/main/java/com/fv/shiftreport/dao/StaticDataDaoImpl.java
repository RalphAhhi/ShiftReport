package com.fv.shiftreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fv.shiftreport.model.Role;
import com.fv.shiftreport.util.DatabaseUtil;

public class StaticDataDaoImpl implements StaticDataDao{

	public List<Role> fetchAllRole() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,description from fv.role";
			ps = dbConnection.prepareStatement(sql);		
			return buildRoleList( ps.executeQuery());
		}finally {
			if(null!=dbConnection){
				dbConnection.close();
			}
			if(null!=ps){
				ps.close();
			}
			
		}
	}
	
	private List<Role> buildRoleList(ResultSet rs) throws SQLException{
		List<Role> response = new ArrayList<Role>();
		while(rs.next()){
			response.add(this.buildRole(rs));
		}
		return response;
	}
	
	private Role buildRole(ResultSet rs) throws SQLException{
		Role response =  new Role();
		response.setId(rs.getInt("id"));
		response.setDescription(rs.getString("description"));
		return response;
	}

	public List<String> fetchUniqueCustomer(String table) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<String> customers = new ArrayList<String>();
		try{
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select distinct(customer) from fv."+table;
			ps = dbConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				customers.add(rs.getString("customer"));
			}
		}finally {
			if(null!=dbConnection){
				dbConnection.close();
			}
			if(null!=ps){
				ps.close();
			}
			
		}
		return  customers;
	}

}

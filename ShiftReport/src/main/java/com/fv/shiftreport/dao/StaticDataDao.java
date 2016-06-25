package com.fv.shiftreport.dao;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.model.Role;

public interface StaticDataDao {

	public List<Role> fetchAllRole()throws SQLException;

	public List<String> fetchUniqueCustomer(String string)throws SQLException;
}

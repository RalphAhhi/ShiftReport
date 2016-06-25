package com.fv.shiftreport.manager;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.dao.StaticDataDao;
import com.fv.shiftreport.model.Role;

public interface StaticDataManager {
	
	
	public List<Role> getAllRole() throws SQLException;
	public List<String> getAllUniqueCustomerAR()throws SQLException;
	public List<String> getAllUniqueCustomerCollection()throws SQLException;
	public void setStaticDataDao(StaticDataDao staticDataDao);

}

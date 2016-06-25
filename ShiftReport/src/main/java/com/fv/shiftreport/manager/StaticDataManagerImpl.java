package com.fv.shiftreport.manager;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.dao.StaticDataDao;
import com.fv.shiftreport.model.Role;

public class StaticDataManagerImpl implements StaticDataManager {
	
	private StaticDataDao staticDataDao;

	public void setStaticDataDao(StaticDataDao staticDataDao) {
		this.staticDataDao = staticDataDao;
	}


	public List<Role> getAllRole() throws SQLException {
		return staticDataDao.fetchAllRole();
	}


	public List<String> getAllUniqueCustomerAR() throws SQLException {
		return staticDataDao.fetchUniqueCustomer("AccountReceivable");
	}


	public List<String> getAllUniqueCustomerCollection() throws SQLException {
		return staticDataDao.fetchUniqueCustomer("Collection");
	}
	

}

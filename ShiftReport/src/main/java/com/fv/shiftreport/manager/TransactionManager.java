package com.fv.shiftreport.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.dao.TransactionDao;
import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;

public interface TransactionManager {
	
	public void saveUpdate(Transaction transactions) throws SQLException, Exception;
	public void setTransactionDao(TransactionDao transactionDao);
	public List<Transaction> getAllTransactions() throws SQLException;
	public Transaction getTransactionByUtrxId(String selectedRowId) throws SQLException;
	public List<AccountReceivable> getAccountReceivableByCriteria(AccountReceivable ar, Date dateFrom, Date dateTo) throws SQLException ;
	public List<Collection> getCollectionByCriteria(Collection collection, Date dateFrom, Date dateTo) throws SQLException ;
	public void synchronizeTransations(List<Transaction> trxList,UserResponse user) throws SQLException;
	public List<AccountReceivable> getallAccountReceivable()throws SQLException;
	public List<Collection> getallCollections()throws SQLException;
}

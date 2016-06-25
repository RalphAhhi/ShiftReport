package com.fv.shiftreport.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;

public interface TransactionDao {

	public void saveTransaction(Transaction transactions) throws SQLException;
	public List<Transaction> fetchTransactions() throws SQLException;
	public Transaction fetchTransactionByUtrxId(String universalTrxId)throws SQLException;
	public List<Transaction> fetchTransactionByUtrxIds(String universalTrxIds)throws SQLException;
	public void updateTransaction(Transaction transactions) throws SQLException;
	public List<Collection> fetchCollectionByCriteria(Collection collection, Date dateFrom, Date dateTo) throws SQLException ;
	public List<AccountReceivable> fetchAccountReceivableByCriteria(AccountReceivable ar, Date dateFrom, Date dateTo) throws SQLException ;
	public void auditSynch(String string, String string2, UserResponse user) throws SQLException;
	public List<AccountReceivable> fetchAllAccountReceivable() throws SQLException;
	public List<Collection> fetchAllCollections()throws SQLException;

}

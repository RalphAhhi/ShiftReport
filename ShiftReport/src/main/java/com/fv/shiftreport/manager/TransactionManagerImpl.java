package com.fv.shiftreport.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.dao.TransactionDao;
import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.Util;

public class TransactionManagerImpl implements TransactionManager {
	
	private TransactionDao transactionDao;
	
	private static final String TRANSACTION = "Transaction";

	public void saveUpdate(Transaction transactions) throws SQLException, Exception{
		if(null != transactions.getUniversalTrxId() && 0 != transactions.getId()){
			transactionDao.updateTransaction(transactions);
		}else{
			transactions.setUniversalTrxId(Util.getMacAddress()+DatabaseUtil.sqlGetLastId(TRANSACTION));
			transactionDao.saveTransaction(transactions);	
		}
		
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public List<Transaction> getAllTransactions() throws SQLException {
		return transactionDao.fetchTransactions();
		
	}

	public Transaction getTransactionByUtrxId(String universalTrxId) throws SQLException {
		return transactionDao.fetchTransactionByUtrxId(universalTrxId);
	}

	public List<AccountReceivable> getAccountReceivableByCriteria(AccountReceivable ar, Date dateFrom, Date dateTo) throws SQLException {
		return transactionDao.fetchAccountReceivableByCriteria(ar, dateFrom, dateTo);
	}

	public List<Collection> getCollectionByCriteria(Collection collection, Date dateFrom, Date dateTo) throws SQLException {
		return transactionDao.fetchCollectionByCriteria(collection,dateFrom,dateTo);
	}

	public void synchronizeTransations(List<Transaction> trxListToImport, UserResponse user) throws SQLException {
		StringBuilder sbNew = new StringBuilder();
		StringBuilder sbUpdate = new StringBuilder();
		try{
			for(Transaction trx: trxListToImport){
				Transaction dbTrx = transactionDao.fetchTransactionByUtrxId(trx.getUniversalTrxId());
				if(null==dbTrx){
					transactionDao.saveTransaction(trx);
					sbNew.append(trx.getUniversalTrxId());
					sbNew.append(",");
				}else if(null!=dbTrx && isDbDataOutDated(trx,dbTrx)){
					transactionDao.updateTransaction(trx);
					sbUpdate.append(trx.getUniversalTrxId());
					sbUpdate.append(",");
				}
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally {
			if(sbNew.length()>0 || sbUpdate.length()>0){
				transactionDao.auditSynch(sbNew.toString(),sbUpdate.toString(),user);
			}
		}
		
	}

	private boolean isDbDataOutDated(Transaction trx, Transaction dbTrx) {
		if(dbTrx.getCreatedDate().before(trx.getCreatedDate())){
			return true;
		}else if(null!=trx.getUpdatedDate() && dbTrx.getUpdatedDate()==null){
			return true;
		}else if(null != dbTrx.getUpdatedDate() && null != trx.getUpdatedDate() 
				&& dbTrx.getUpdatedDate().before(trx.getUpdatedDate())){
			return true;
		}
		return false;
	}

	public List<AccountReceivable> getallAccountReceivable() throws SQLException {
		return transactionDao.fetchAllAccountReceivable();
		
	}

	public List<Collection> getallCollections() throws SQLException {
		return transactionDao.fetchAllCollections();
	}
	
	
}

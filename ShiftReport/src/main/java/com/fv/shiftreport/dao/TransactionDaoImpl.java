package com.fv.shiftreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.SubTransaction;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.Util;

public class TransactionDaoImpl implements TransactionDao{

	public void saveTransaction(Transaction transaction) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			
			String sql = "insert into fv.transaction (gasTotalAmount, lubeTotalAmount, avgasUgtInventory, avgasOpening"
					+ ",avgasDelivery, avgasTotal, avgasClosing, avgasSTK, avgasMeter, dieselUgtInvt, dieselOpening"
					+ ",dieselDelivery, dieselTotal, dieselClosing, dieselSTK, dieselMeter, fuelSales, addLubes"
					+ ",totalSales, LessAR, totalCashSales, addCollection, lessExpenses, totalCashOnHand, "
					+ "cashDeposited,totalCollection,createdBy,createdDate,updatedBy,updatedDate,universaltrxid,overshort)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			setTransactionParam(ps, transaction);
			ps.execute() ;
			
			
			saveSubTransactions(transaction.getSubTransactionList(),transaction.getUniversalTrxId(),dbConnection);
			saveCollection(transaction.getCollectionList(),transaction.getUniversalTrxId(),dbConnection);
			saveAccountReceivable(transaction.getAccountReceivableList(),transaction.getUniversalTrxId(),dbConnection);
			dbConnection.commit();
			
		}catch(Exception e){
			dbConnection.rollback();
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
	}
	
	private void saveAccountReceivable(List<AccountReceivable> accountReceivableList, String universalTrxId, Connection dbConnection) throws SQLException {
		String sql = "insert into fv.accountreceivable (customer,InvoiceNo,Amount,universaltrxid) values (?,?,?,?)";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		for(AccountReceivable ar: accountReceivableList){
			ar.setUniversalTrxId(universalTrxId);
			ps.setString(1, ar.getCustomer());
			ps.setString(2, ar.getInvoiceNo());
			ps.setDouble(3, ar.getAmount());
			ps.setString(4, universalTrxId);
			ps.addBatch();
		}
		ps.executeBatch();
		
	}
	
	private void saveCollection(List<Collection> collectionList, String universalTrxId, Connection dbConnection) throws SQLException {
		String sql = "insert into fv.collection (customer,bankcheck,Amount,universalTrxId) values (?,?,?,?)";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		for(Collection collection: collectionList){
			collection.setUniversalTrxId(universalTrxId);
			ps.setString(1, collection.getCustomer());
			ps.setString(2, collection.getBankCheck());
			ps.setDouble(3, collection.getAmount());
			ps.setString(4, universalTrxId);
			ps.addBatch();
		}
		ps.executeBatch();
		
	}
	
	private void saveSubTransactions(List<SubTransaction> subTransaction, String universalTrxId, Connection dbConnection) throws SQLException{
		String sql = "insert into fv.subtransaction (pumpnumber,productId,opening,closing"
				+ ",liters,calib,sold,price,amount,universaltrxid) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		for(SubTransaction st: subTransaction){
			st.setUniversalTrxId(universalTrxId);
			ps.setInt(1, st.getPumpNumber());
			ps.setInt(2, st.getProductId());
			ps.setDouble(3, st.getOpening());
			ps.setDouble(4, st.getClosing());
			ps.setDouble(5, st.getLiters());
			ps.setDouble(6, st.getCalib());
			ps.setDouble(7, st.getSold());
			ps.setDouble(8, st.getPrice());
			ps.setDouble(9, st.getAmount());
			ps.setString(10, universalTrxId);
			ps.addBatch();
		}
		ps.executeBatch();
	}

	private void setTransactionParam(PreparedStatement ps , Transaction transaction) throws SQLException{
		ps.setDouble(1, transaction.getTotalGas());
		ps.setDouble(2, transaction.getTotalLubes());
		ps.setDouble(3, transaction.getAvgasUgtInvt());
		ps.setDouble(4, transaction.getAvgasOpening());
		ps.setDouble(5, transaction.getAvgasDelivery());
		ps.setDouble(6, transaction.getAvgasTotal());
		ps.setDouble(7, transaction.getAvgasClosing());
		ps.setDouble(8, transaction.getAvgasSTK());
		ps.setDouble(9, transaction.getAvgasMeter());
		ps.setDouble(10, transaction.getDieselUgtInvt());
		ps.setDouble(11, transaction.getDieselOpening());
		ps.setDouble(12, transaction.getDieselDelivery());
		ps.setDouble(13, transaction.getDieselTotal());
		ps.setDouble(14, transaction.getDieselClosing());
		ps.setDouble(15, transaction.getDieselSTK());
		ps.setDouble(16, transaction.getDieselMeter());
		ps.setDouble(17, transaction.getFuelSales());
		ps.setDouble(18, transaction.getAddLubes());
		ps.setDouble(19, transaction.getTotalSales());
		ps.setDouble(20, transaction.getLessAr());
		ps.setDouble(21, transaction.getTotalCashSales());
		ps.setDouble(22, transaction.getAddCollection());
		ps.setDouble(23, transaction.getLessExpenses());
		ps.setDouble(24, transaction.getTotalCashOnHand());
		ps.setDouble(25, transaction.getCashDeposited());
		ps.setDouble(26, transaction.getTotalCollections());
		ps.setString(27, transaction.getCreatedBy());
		ps.setTimestamp(28, transaction.getCreatedDate());
		ps.setString(29, transaction.getUpdatedBy());
		ps.setTimestamp(30, transaction.getUpdatedDate());
		ps.setString(31, transaction.getUniversalTrxId());
		ps.setDouble(32, transaction.getOverShort());
	}
	
   public List<Transaction> fetchTransactions() throws SQLException{
	    Connection dbConnection = null;
		PreparedStatement ps = null;
		List<Transaction> transactionList = null;
		try{
			
			String sql = "select id, gasTotalAmount, lubeTotalAmount, avgasUgtInventory, avgasOpening"
					+ ",avgasDelivery, avgasTotal, avgasClosing, avgasSTK, avgasMeter, dieselUgtInvt, dieselOpening"
					+ ",dieselDelivery, dieselTotal, dieselClosing, dieselSTK, dieselMeter, fuelSales, addLubes"
					+ ",totalSales, LessAR, totalCashSales, addCollection, lessExpenses, totalCashOnHand, "
					+ "cashDeposited,totalCollection,createdBy,createdDate,updatedBy,updatedDate,universaltrxid,overshort"
					+ " from  fv.transaction order by id desc";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery() ;
			transactionList = buildTrxList(rs);
			for(Transaction trx: transactionList){
				trx.setSubTransactionList(fetchSubTrxForTrx(trx.getUniversalTrxId()));
				trx.setCollectionList(fetchCollectionForTrx(trx.getUniversalTrxId()));
				trx.setAccountReceivableList(fetchAccountReceivable(trx.getUniversalTrxId()));
			}
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return transactionList;
   }
   
    private List<AccountReceivable> fetchAccountReceivable(String universalTrxId) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement ps = null;
		List<AccountReceivable> arList = null;
		try {

			String sql = "select id,customer,InvoiceNo,Amount,universaltrxid"
					+ " from  fv.accountreceivable where universalTrxId = ? ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, universalTrxId);
			ResultSet rs = ps.executeQuery() ;
			arList = buildArList(rs,false);
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return arList;

    }

    private List<AccountReceivable> buildArList(ResultSet rs, boolean isSetAudit) throws SQLException {
    	List<AccountReceivable> response = new ArrayList<AccountReceivable>();
		while(rs.next()){
			AccountReceivable ar =this.buildAr(rs);
			if(isSetAudit){
				ar.setCreatedDate(rs.getTimestamp("createddate"));
			}
			response.add(ar);
		}
		return response;
	}

	private AccountReceivable buildAr(ResultSet rs) throws SQLException {
		AccountReceivable response =  new AccountReceivable();
 		response.setId(rs.getInt("id"));
 		response.setCustomer(rs.getString("customer"));
 		response.setInvoiceNo(rs.getString("invoiceNo"));
 		response.setAmount(rs.getDouble("Amount"));
 		response.setUniversalTrxId(rs.getString("universalTrxId"));
 		return response;
	}

	private List<SubTransaction> fetchSubTrxForTrx(String universalTrxId) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<SubTransaction> subtransactionList = null;
		try {

			String sql = "select id, pumpnumber,productId,opening,closing,liters,calib,sold,price,amount,universaltrxid"
					+ " from  fv.subTransaction where universalTrxId = ? ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, universalTrxId);
			ResultSet rs = ps.executeQuery() ;
			subtransactionList = buildSubTrxList(rs);
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return subtransactionList;
	   
   }
   
    private List<SubTransaction> buildSubTrxList(ResultSet rs) throws SQLException {
	   List<SubTransaction> response = new ArrayList<SubTransaction>();
		while(rs.next()){
			response.add(this.buildSubTrx(rs));
		}
		return response;
    }

    private SubTransaction buildSubTrx(ResultSet rs) throws SQLException {
    	SubTransaction response =  new SubTransaction();
		response.setId(rs.getInt("id"));
		response.setPumpNumber(rs.getInt("pumpnumber"));
		response.setProductId(rs.getInt("productId"));
		response.setOpening(rs.getDouble("opening"));
		response.setClosing(rs.getDouble("closing"));
		response.setLiters(rs.getDouble("liters"));
		response.setCalib(rs.getDouble("calib"));
		response.setSold(rs.getDouble("sold"));
		response.setPrice(rs.getDouble("price"));
		response.setAmount(rs.getDouble("amount"));
		response.setUniversalTrxId(rs.getString("universaltrxid"));
		return response;
    }
    
    private List<Collection> fetchCollectionForTrx(String universalTrxId) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<Collection> collectionList = null;
		try {

			String sql = "select id,customer,bankcheck,Amount,universalTrxId"
					+ " from  fv.collection where universalTrxId = ? ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, universalTrxId);
			ResultSet rs = ps.executeQuery() ;
			collectionList = buildCollectionList(rs,false);
			
		}catch(Exception e){
			Util.writeToFile("error", e.getMessage());
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return collectionList;
	   
   }
    
    private List<Collection> buildCollectionList(ResultSet rs,boolean isAuditSet) throws SQLException {
 	   List<Collection> response = new ArrayList<Collection>();
 		while(rs.next()){
 			Collection c =this.buildCollection(rs);
 			if(isAuditSet){
 				c.setCreatedDate(rs.getTimestamp("createddate"));
 			}
 			response.add(c);
 		}
 		return response;
     }

     private Collection buildCollection(ResultSet rs) throws SQLException {
    	Collection response =  new Collection();
 		response.setId(rs.getInt("id"));
 		response.setCustomer(rs.getString("customer"));
 		response.setBankCheck(rs.getString("bankcheck"));
 		response.setAmount(rs.getDouble("Amount"));
 		response.setUniversalTrxId(rs.getString("universalTrxId"));
 		return response;
     }

    

    private List<Transaction> buildTrxList(ResultSet rs) throws SQLException{
	   List<Transaction> response = new ArrayList<Transaction>();
		while(rs.next()){
			response.add(this.buildTrx(rs));
		}
		return response;
   }
   
	
	private Transaction buildTrx(ResultSet rs) throws SQLException{
		Transaction response =  new Transaction();
		response.setId(rs.getInt("id"));
		response.setAddLubes(rs.getDouble("addLubes"));
		response.setAvgasDelivery(rs.getDouble("avgasDelivery"));
		response.setAvgasTotal(rs.getDouble("avgasTotal"));
		response.setAvgasClosing(rs.getDouble("avgasClosing"));
		response.setAvgasSTK(rs.getDouble("avgasSTK"));
		response.setAvgasMeter(rs.getDouble("avgasMeter"));
		response.setTotalGas(rs.getDouble("gasTotalAmount"));
		response.setTotalLubes(rs.getDouble("lubeTotalAmount"));
		response.setAvgasUgtInvt(rs.getDouble("avgasUgtInventory"));
		response.setAvgasOpening(rs.getDouble("avgasOpening"));
		response.setDieselUgtInvt(rs.getDouble("dieselUgtInvt"));
		response.setDieselOpening(rs.getDouble("dieselOpening"));
		response.setDieselDelivery(rs.getDouble("dieselDelivery"));
		response.setDieselTotal(rs.getDouble("dieselTotal"));
		response.setDieselClosing(rs.getDouble("dieselClosing"));
		response.setDieselSTK(rs.getDouble("dieselSTK"));
		response.setDieselMeter(rs.getDouble("dieselMeter"));
		response.setFuelSales(rs.getDouble("fuelSales"));
		response.setTotalSales(rs.getDouble("totalSales"));
		response.setLessAr(rs.getDouble("LessAR"));
		response.setTotalCashSales(rs.getDouble("totalCashSales"));
		response.setAddCollection(rs.getDouble("addCollection"));
		response.setLessExpenses(rs.getDouble("lessExpenses"));
		response.setTotalCashOnHand(rs.getDouble("totalCashOnHand"));
		response.setCashDeposited(rs.getDouble("cashDeposited"));
		response.setTotalCollections(rs.getDouble("totalCollection"));
		response.setCreatedBy(rs.getString("createdBy"));
		response.setCreatedDate(rs.getTimestamp("createdDate"));
		response.setUpdatedBy(rs.getString("updatedBy"));
		response.setUpdatedDate(rs.getTimestamp("updatedDate"));
		response.setUniversalTrxId(rs.getString("universaltrxid"));
		response.setOverShort(rs.getDouble("overshort"));
		return response;
	}

	public Transaction fetchTransactionByUtrxId(String universalTrxId) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		Transaction trx = null;
		try{
			
			String sql = "select id, gasTotalAmount, lubeTotalAmount, avgasUgtInventory, avgasOpening"
					+ ",avgasDelivery, avgasTotal, avgasClosing, avgasSTK, avgasMeter, dieselUgtInvt, dieselOpening"
					+ ",dieselDelivery, dieselTotal, dieselClosing, dieselSTK, dieselMeter, fuelSales, addLubes"
					+ ",totalSales, LessAR, totalCashSales, addCollection, lessExpenses, totalCashOnHand, "
					+ "cashDeposited,totalCollection,createdBy,createdDate,updatedBy,updatedDate,universaltrxid,overshort"
					+ " from  fv.transaction where universaltrxid = ?";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, universalTrxId);
			ResultSet rs = ps.executeQuery() ;
			rs.next();
			trx = buildTrx(rs);
		    trx.setSubTransactionList(fetchSubTrxForTrx(trx.getUniversalTrxId()));
			trx.setCollectionList(fetchCollectionForTrx(trx.getUniversalTrxId()));
			trx.setAccountReceivableList(fetchAccountReceivable(trx.getUniversalTrxId()));
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return trx;
	}

	public void updateTransaction(Transaction transaction) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			
			String sql = "update fv.transaction set gasTotalAmount = ?, lubeTotalAmount = ?, avgasUgtInventory = ?, avgasOpening = ?"
					+ ",avgasDelivery = ?, avgasTotal = ?, avgasClosing = ?, avgasSTK = ?, "
					+ "avgasMeter = ?, dieselUgtInvt = ?, dieselOpening = ?,dieselDelivery = ?"
					+ ", dieselTotal = ?, dieselClosing = ?, dieselSTK = ?, dieselMeter = ?"
					+ ", fuelSales = ?, addLubes = ?, totalSales = ?, LessAR = ?, "
					+ "totalCashSales = ?, addCollection = ?, lessExpenses = ?, totalCashOnHand = ?, "
					+ "cashDeposited = ?,totalCollection = ?, updatedBy = ?,updatedDate = ?, overshort=? "
					+ "where universaltrxid = ? ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			setUpdateTransactionParam(ps, transaction);
			ps.executeUpdate() ;
			
			updateSubTransactions(transaction.getSubTransactionList(),transaction.getUniversalTrxId(),dbConnection);
		    updateCollection(transaction.getCollectionList(), transaction.getUniversalTrxId(), dbConnection);
			updateAccountReceivable(transaction.getAccountReceivableList(), transaction.getUniversalTrxId(), dbConnection);
			dbConnection.commit();
			
		}catch(Exception e){
			dbConnection.rollback();
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
	}
	
	private void updateAccountReceivable(List<AccountReceivable> arList, String universalTrxId, Connection dbConnection) throws SQLException {
		deleteAccountReceivable(universalTrxId, dbConnection);
		saveAccountReceivable(arList, universalTrxId, dbConnection);
	}
	
	
	
	private void updateCollection(List<Collection> collectionList, String universalTrxId,
			Connection dbConnection) throws SQLException {
		deleteCollection(universalTrxId, dbConnection);
	    saveCollection(collectionList, universalTrxId, dbConnection);
	}
	
	private void deleteCollection( String universalTrxId,Connection dbConnection) throws SQLException {
		
			String sql = "delete from fv.Collection where universaltrxid = ?";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, universalTrxId);
			ps.execute() ;
		
	}
	
	private void deleteAccountReceivable( String universalTrxId,
			Connection dbConnection) throws SQLException {
		String sql = "delete from fv.accountreceivable where universaltrxid = ?";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setString(1, universalTrxId);
		ps.execute() ;
	}
	
	private void updateSubTransactions(List<SubTransaction> subTransactionList, String universalTrxId,
			Connection dbConnection) throws SQLException {
		String sql = "update fv.subtransaction set pumpnumber = ?,productId = ?,opening = ?,closing = ?"
				+ ",liters = ?,calib = ?,sold = ?,price = ?,amount = ? where universaltrxid = ? ";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		for(SubTransaction st: subTransactionList){
			st.setUniversalTrxId(universalTrxId);
			ps.setInt(1, st.getPumpNumber());
			ps.setInt(2, st.getProductId());
			ps.setDouble(3, st.getOpening());
			ps.setDouble(4, st.getClosing());
			ps.setDouble(5, st.getLiters());
			ps.setDouble(6, st.getCalib());
			ps.setDouble(7, st.getSold());
			ps.setDouble(8, st.getPrice());
			ps.setDouble(9, st.getAmount());
			ps.setString(10, universalTrxId);
			ps.addBatch();
		}
		ps.executeBatch();
		
	}

	private void setUpdateTransactionParam(PreparedStatement ps , Transaction transaction) throws SQLException{
		ps.setDouble(1, transaction.getTotalGas());
		ps.setDouble(2, transaction.getTotalLubes());
		ps.setDouble(3, transaction.getAvgasUgtInvt());
		ps.setDouble(4, transaction.getAvgasOpening());
		ps.setDouble(5, transaction.getAvgasDelivery());
		ps.setDouble(6, transaction.getAvgasTotal());
		ps.setDouble(7, transaction.getAvgasClosing());
		ps.setDouble(8, transaction.getAvgasSTK());
		ps.setDouble(9, transaction.getAvgasMeter());
		ps.setDouble(10, transaction.getDieselUgtInvt());
		ps.setDouble(11, transaction.getDieselOpening());
		ps.setDouble(12, transaction.getDieselDelivery());
		ps.setDouble(13, transaction.getDieselTotal());
		ps.setDouble(14, transaction.getDieselClosing());
		ps.setDouble(15, transaction.getDieselSTK());
		ps.setDouble(16, transaction.getDieselMeter());
		ps.setDouble(17, transaction.getFuelSales());
		ps.setDouble(18, transaction.getAddLubes());
		ps.setDouble(19, transaction.getTotalSales());
		ps.setDouble(20, transaction.getLessAr());
		ps.setDouble(21, transaction.getTotalCashSales());
		ps.setDouble(22, transaction.getAddCollection());
		ps.setDouble(23, transaction.getLessExpenses());
		ps.setDouble(24, transaction.getTotalCashOnHand());
		ps.setDouble(25, transaction.getCashDeposited());
		ps.setDouble(26, transaction.getTotalCollections());
		ps.setString(27, transaction.getUpdatedBy());
		ps.setTimestamp(28, transaction.getUpdatedDate());
		ps.setDouble(29, transaction.getOverShort());
		ps.setString(30, transaction.getUniversalTrxId());
	}

	public List<Collection> fetchCollectionByCriteria(Collection collection, Date dateFrom, Date dateTo) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			
			String sql = "select col.id, col.customer, col.Bancheck, col.Amount from fv.collection col"
					+ " inner join fv.transaction t on col.universalTrxId = ar.universalTrxId where  1 = 1 ";
			if(Util.isNotEmpty(collection.getCustomer())){
				sql.concat(" col.customer = ?");
			}
			if(null!=dateFrom && null!= dateTo){
				sql.concat(" t.createddate between ? and ?");
			}
			int paramIndex = 0;
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			if(Util.isNotEmpty(collection.getCustomer())){
				ps.setString(paramIndex++, collection.getCustomer());
			}
			if(null!=dateFrom && null!= dateTo){
				ps.setDate(paramIndex++, DatabaseUtil.converToJdcbDate(dateFrom));
				ps.setDate(paramIndex++, DatabaseUtil.converToJdcbDate(dateTo));
			}
			ResultSet rs = ps.executeQuery() ;
			return buildCollectionList(rs,false);
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
	}

	public List<AccountReceivable> fetchAccountReceivableByCriteria(AccountReceivable ar, Date dateFrom, Date dateTo) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			
			String sql = "select ar.id, ar.customer, ar.InvoiceNo, ar.Amount from fv.accountreceivable ar"
					+ " inner join fv.transaction t on t.universalTrxId = ar.universalTrxId where  1 = 1 ";
			if(Util.isNotEmpty(ar.getCustomer())){
				sql.concat(" ar.customer = ?");
			}
			if(null!=dateFrom && null!= dateTo){
				sql.concat(" t.createddate between ? and ?");
			}
			int paramIndex = 0;
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			if(Util.isNotEmpty(ar.getCustomer())){
				ps.setString(paramIndex++, ar.getCustomer());
			}
			if(null!=dateFrom && null!= dateTo){
				ps.setDate(paramIndex++, DatabaseUtil.converToJdcbDate(dateFrom));
				ps.setDate(paramIndex++, DatabaseUtil.converToJdcbDate(dateTo));
			}
			ResultSet rs = ps.executeQuery() ;
			return buildArList(rs,false);
		
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
	}

	public List<Transaction> fetchTransactionByUtrxIds(String universalTrxIds) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<Transaction> trxList = null;
		try{
			
			String sql = "select id, gasTotalAmount, lubeTotalAmount, avgasUgtInventory, avgasOpening"
					+ ",avgasDelivery, avgasTotal, avgasClosing, avgasSTK, avgasMeter, dieselUgtInvt, dieselOpening"
					+ ",dieselDelivery, dieselTotal, dieselClosing, dieselSTK, dieselMeter, fuelSales, addLubes"
					+ ",totalSales, LessAR, totalCashSales, addCollection, lessExpenses, totalCashOnHand, "
					+ "cashDeposited,totalCollection,createdBy,createdDate,updatedBy,updatedDate,universaltrxid,overshort"
					+ " from  fv.transaction where universaltrxid in ("+universalTrxIds+")";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery() ;
			trxList = buildTrxList(rs);
			for(Transaction trx: trxList){
				trx.setSubTransactionList(fetchSubTrxForTrx(trx.getUniversalTrxId()));
				trx.setCollectionList(fetchCollectionForTrx(trx.getUniversalTrxId()));
				trx.setAccountReceivableList(fetchAccountReceivable(trx.getUniversalTrxId()));
			}
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return trxList;
	}

	public void auditSynch(String newTrx, String updatedTrx, UserResponse user) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			
			String sql = "insert into fv.synchaudit (insertedtransaction,updatedtransaction,synchby,synchdate)"
					+ " values (?,?,?,?)";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1,newTrx );
			ps.setString(2,updatedTrx );
			ps.setString(3, user.getId());
			ps.setTimestamp(4, DatabaseUtil.getCurrentTimeStamp());
			ps.execute() ;
		
			dbConnection.commit();
			
		}catch(Exception e){
			dbConnection.rollback();
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		
	}

	public List<AccountReceivable> fetchAllAccountReceivable() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<AccountReceivable> arList = null;
		try {

			String sql = "select ar.id,ar.customer,ar.InvoiceNo,ar.Amount,ar.universaltrxid, t.createddate"
					+ " from  fv.accountreceivable ar left join  fv.transaction t on "
					+ " t.universaltrxid = ar.universaltrxid ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery() ;
			arList = buildArList(rs,true);
			
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return arList;

	}

	public List<Collection> fetchAllCollections() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		List<Collection> collectionList = null;
		try {

			String sql = "select c.id,c.customer,c.bankcheck,c.Amount,c.universalTrxId, t.createddate"
					+ " from  fv.collection c left join fv.transaction t "
					+ " on c.universalTrxId = t.universalTrxId ";
			dbConnection = DatabaseUtil.getDBConnection();
			ps = dbConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery() ;
			collectionList = buildCollectionList(rs,true);
			
		}catch(Exception e){
			Util.writeToFile("error", e.getMessage());
			throw new SQLException(e);
		}finally{
			if(null!=dbConnection){
				dbConnection.close();	
			}
		}
		return collectionList;

	}

	
}

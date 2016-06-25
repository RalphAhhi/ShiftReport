package com.fv.shiftreport.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.fv.shiftreport.manager.AccountManager;
import com.fv.shiftreport.manager.TransactionManager;
import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.SubTransaction;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.GasolineTypes;
import com.fv.shiftreport.util.Util;
import com.fv.shiftreport.view.MainView;
import com.fv.shiftreport.view.TransactionListView;

public class TransactionListController {

	protected TransactionListView view;
	protected TransactionManager transactionManager;
	private MainView mainView;
	protected AccountManager accountManager;
	

	public TransactionListController() {
	}

	public void init(MainView mainView)  {
		try{
           this.mainView = mainView;
           List<Transaction> trxList = transactionManager.getAllTransactions();
           DefaultTableModel model = (DefaultTableModel)view.getTblTrxList().getModel();
           for(Transaction trx: trxList){
        	   UserResponse creator = accountManager.getUserById(trx.getCreatedBy());
        	   UserResponse updatedBy = accountManager.getUserById(trx.getUpdatedBy());
        	   model.addRow(new Object[]{trx.getUniversalTrxId(),creator.getFirstName()+"-"+creator.getLastName(),trx.getCreatedDate(),
        			   updatedBy==null?"":updatedBy.getFirstName()+"-"+updatedBy.getLastName(),trx.getUpdatedDate()==null?"":trx.getUpdatedDate()});
        	   
           }
           view.getBtnLoad().addActionListener(new LoadTransactionActionListener());
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public TransactionListView getView() {
		return view;
	}

	public void setView(TransactionListView view) {
		this.view = view;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	

	public class LoadTransactionActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(view.getTblTrxList().getSelectedRow()!= -1){
				Object selectedRowUTrxId = view.getTblTrxList().getModel().getValueAt(view.getTblTrxList().getSelectedRow(), 0);
				try {
					Transaction trx = transactionManager.getTransactionByUtrxId(selectedRowUTrxId.toString());
//					UserResponse user = mainView.getLoggedInUser();
//	                if(user.getId().equals(trx.getCreatedBy())|| user.getRoleId() == RoleConstant.ADMIN_ROLE){
						loadTransaction(trx);
//					}else{
//						 LoginDialog loginDlg = new LoginDialog(mainView);
//						 AccountManager accountManager = new AccountManagerImpl();
//						 accountManager.setUserDao(new UserDaoImpl());
//						 loginDlg.setAccountManager(accountManager);
//						 
//	                     loginDlg.setVisible(true);
//	                     if(loginDlg.isSucceeded()){
//	                    	 loadTransaction(trx);
//	                     }
//					}
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(mainView, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}
	
	private void loadTransaction(Transaction trx){
		mainView.setCurrentTrx(trx);
		setTransactionToMainView(trx);
		view.dispose();
	}
	
	private void setTransactionToMainView(Transaction trx){
		mainView.getTxtClosingD().setText(Util.toString(trx.getDieselClosing()));
		mainView.getTxtDeliveryD().setText(Util.toString(trx.getDieselDelivery()));
		mainView.getTxtMeterD().setText(Util.toString(trx.getDieselMeter()));
		mainView.getTxtOpeningD().setText(Util.toString(trx.getDieselOpening()));
		mainView.getTxtSTKD().setText(Util.toString(trx.getDieselSTK()));
		mainView.getTxtTotalD().setText(Util.toString(trx.getDieselTotal()));
		mainView.getTxtUgtD().setText(Util.toString(trx.getDieselUgtInvt()));
		mainView.getTxtTotal().setText(Util.toString(trx.getTotalGas()));
		mainView.getTxtTotalA().setText(Util.toString(trx.getAvgasTotal()));
		mainView.getTxtTotalD().setText(Util.toString(trx.getDieselOpening()));
		mainView.getTxtClosingA().setText(Util.toString(trx.getAvgasClosing()));
		mainView.getTxtDeliveryA().setText(Util.toString(trx.getAvgasDelivery()));
		mainView.getTxtMeterA().setText(Util.toString(trx.getAvgasMeter()));
		mainView.getTxtOpeningA().setText(Util.toString(trx.getAvgasOpening()));
		mainView.getTxtSDKA().setText(Util.toString(trx.getAvgasSTK()));
		mainView.getTxtTotalA().setText(Util.toString(trx.getAvgasTotal()));
		mainView.getTxtUgtA().setText(Util.toString(trx.getAvgasUgtInvt()));
		mainView.getTxtTotalSales().setText(Util.toString(trx.getTotalSales()));
		mainView.getTxtLubeTotal().setText(Util.toString(trx.getTotalLubes()));
		mainView.getTxtFuelSales().setText(Util.toString(trx.getFuelSales()));
		mainView.getTxtAddLubes().setText(Util.toString(trx.getAddLubes()));
		mainView.getTxtLessAr().setText(Util.toString(trx.getLessAr()));
		mainView.getTxtTotalCashOnHand().setText(Util.toString(trx.getTotalCashOnHand()));
		mainView.getTxtCashDeposited().setText(Util.toString(trx.getCashDeposited()));
		mainView.getTxtOverShort().setText(Util.toString(trx.getOverShort()));
		mainView.getTxtTotalCashSales().setText(Util.toString(trx.getTotalCashSales()));
		mainView.getTxtTotalCollection().setText(Util.toString(trx.getTotalCollections()));
		mainView.getTxtAddCollection().setText(Util.toString(trx.getAddCollection()));
		mainView.getTxtLessExpenses().setText(Util.toString(trx.getLessExpenses()));
        for(SubTransaction subTrx : trx.getSubTransactionList()){
			loadSubTrxToMain(subTrx);
		}
        loadCollectionTable(trx.getCollectionList());
        loadArTable(trx.getAccountReceivableList());
		
        
        
	}
	
	private void loadCollectionTable(List<Collection> collectionList){
		JTable table = mainView.getTblCollection();
       
		for(Collection col : collectionList){
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.addRow(new Object[]{col.getCustomer(),
					col.getBankCheck(),col.getAmount(),col.getId()});
		}
		
	}
	
	private void loadArTable(List<AccountReceivable> arList){
		JTable table = mainView.getReceivableTable();
       
		for(AccountReceivable ar : arList){
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.addRow(new Object[]{ar.getCustomer(),
					ar.getInvoiceNo(),ar.getAmount(),ar.getId()});
		}
		
	}
	
	private void loadSubTrxToMain(SubTransaction subTrx){
		GasolineTypes gasType = GasolineTypes.getGasTypeId(subTrx.getProductId());
		if(null!=gasType){
			if(gasType == GasolineTypes.AVGAS){
				loadGasToTable(mainView.getAvgasTable(), subTrx);
			}else if(gasType == GasolineTypes.DIESEL){
				loadGasToTable(mainView.getDieselTable(), subTrx);
			}else{
				loadGasToTable(mainView.getKeroTable(), subTrx);
			}
		}else{
			loadLubesToTable(subTrx);
		}
	}
	
	private void loadLubesToTable(SubTransaction subTrx){
		JTable table = mainView.getLubeTable();
		int rowselected = -1;
		for(int x=0;x<table.getRowCount();x++){
			Integer productId = (Integer)table.getModel().getValueAt(x, 0);
			if(subTrx.getProductId()==productId){
				rowselected  = x;
				break;
			}
		}
		if(rowselected>-1){
			table.getModel().setValueAt(subTrx.getOpening(), rowselected, 2);
			table.getModel().setValueAt(subTrx.getClosing(), rowselected, 3);
			table.getModel().setValueAt(subTrx.getSold(), rowselected, 4);
			table.getModel().setValueAt(subTrx.getPrice(), rowselected, 5);
			table.getModel().setValueAt(subTrx.getAmount(), rowselected, 6);
			table.getModel().setValueAt(subTrx.getId(), rowselected, 7);
		}
		
	}
	
	private void loadGasToTable(JTable table, SubTransaction subTrx){
		int row = subTrx.getPumpNumber()-1;
		table.getModel().setValueAt(subTrx.getClosing(), row, 1);
		table.getModel().setValueAt(subTrx.getOpening(), row, 2);
		table.getModel().setValueAt(subTrx.getLiters(), row, 3);
		table.getModel().setValueAt(subTrx.getCalib(), row, 4);
		table.getModel().setValueAt(subTrx.getSold(), row, 5);
		table.getModel().setValueAt(subTrx.getPrice(), row, 6);
		table.getModel().setValueAt(subTrx.getAmount(), row, 7);
		table.getModel().setValueAt(subTrx.getId(), row, 8);
	}
}

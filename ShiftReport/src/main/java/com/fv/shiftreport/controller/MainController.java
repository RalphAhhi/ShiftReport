package com.fv.shiftreport.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fv.shiftreport.dao.ProductDaoImpl;
import com.fv.shiftreport.dao.StaticDataDaoImpl;
import com.fv.shiftreport.dao.TransactionDaoImpl;
import com.fv.shiftreport.dao.UserDaoImpl;
import com.fv.shiftreport.manager.AccountManager;
import com.fv.shiftreport.manager.AccountManagerImpl;
import com.fv.shiftreport.manager.ProductManager;
import com.fv.shiftreport.manager.ProductManagerImpl;
import com.fv.shiftreport.manager.StaticDataManager;
import com.fv.shiftreport.manager.StaticDataManagerImpl;
import com.fv.shiftreport.manager.TransactionManager;
import com.fv.shiftreport.manager.TransactionManagerImpl;
import com.fv.shiftreport.model.AccountReceivable;
import com.fv.shiftreport.model.Collection;
import com.fv.shiftreport.model.Products;
import com.fv.shiftreport.model.Role;
import com.fv.shiftreport.model.SubTransaction;
import com.fv.shiftreport.model.SynchData;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.GasolineTypes;
import com.fv.shiftreport.util.RoleConstant;
import com.fv.shiftreport.util.Util;
import com.fv.shiftreport.view.InventoryView;
import com.fv.shiftreport.view.LoginView;
import com.fv.shiftreport.view.MainView;
import com.fv.shiftreport.view.RegisterView;
import com.fv.shiftreport.view.TransactionListView;

public class MainController {

	protected MainView mainView;
	protected ProductManager productManager;
	protected TransactionManager trxManager;
	protected StaticDataManager staticDataManager;
	protected AccountManager accountManager;

	public MainController() {
	}

	public void init()  {
		try{
			productManager = new ProductManagerImpl();
			productManager.setProductDao(new ProductDaoImpl());
			trxManager = new TransactionManagerImpl();
			trxManager.setTransactionDao(new TransactionDaoImpl());
			staticDataManager = new StaticDataManagerImpl();
			staticDataManager.setStaticDataDao(new StaticDataDaoImpl());
			accountManager = new AccountManagerImpl();
			accountManager.setUserDao(new UserDaoImpl());
			
			initTableSetting();
			mainView.getBtnAdd().addActionListener(new AddActionListener());
			mainView.getTxtName().setText(mainView.getLoggedInUser().getLastName()+", "+mainView.getLoggedInUser().getFirstName());
			mainView.getBtnAddCollection().addActionListener(new AddCollectionActionListener());
			mainView.getBtnSave().addActionListener(new SaveActionListener());
			mainView.getJMenuBar().getMenu(0).getItem(0).addActionListener(new LoadTransactionListener());
			mainView.getJMenuBar().getMenu(0).getItem(1).addActionListener(new NewTransactionListener());
			mainView.getJMenuBar().getMenu(0).getItem(2).addActionListener(new RegisterActionListener());
			mainView.getJMenuBar().getMenu(0).getItem(3).addActionListener(new LogOutActionListener());
			mainView.getJMenuBar().getMenu(1).getItem(0).addActionListener(new ExportDataListener());
			mainView.getJMenuBar().getMenu(1).getItem(1).addActionListener(new ImportDataListener());
//			mainView.getJMenuBar().getMenu(0).getItem(1).addActionListener(new SynchNetworkListener());
			mainView.getJMenuBar().getMenu(2).getItem(0).addActionListener(new InventoryListener());
			mainView.getBtnDelete().addActionListener(new DeleteArActionListener());
			mainView.getBtnDeleteCollection().addActionListener(new DeleteCollectionActionListener());
			mainView.getTxtLessExpenses().addKeyListener(new KeyPressSummaryListener());
			mainView.getTxtCashDeposited().addKeyListener(new KeyPressSummaryListener());
		}catch(Exception e){
			Util.writeToFile("error", e.getMessage());
			JOptionPane.showMessageDialog(mainView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void initTableSetting(){
		List<Products> productList;
		DefaultTableModel model = (DefaultTableModel)mainView.getLubeTable().getModel();
		try {
			mainView.getLubeTable().setModel(model);
			mainView.getLubeTable().setRowHeight(18);
			productList = productManager.getLubesProducts();
			mainView.getLubeTable().removeColumn(mainView.getLubeTable().getColumnModel().getColumn(0));
			mainView.getLubeTable().removeColumn(mainView.getLubeTable().getColumnModel().getColumn(6));
			mainView.getTblCollection().getColumnModel().getColumn(3).setResizable(false);
			mainView.getTblCollection().getColumnModel().getColumn(3).setPreferredWidth(15);
			mainView.getTblCollection().getColumnModel().getColumn(3).setMinWidth(0);
			mainView.getTblCollection().getColumnModel().getColumn(3).setMaxWidth(0);
			mainView.getReceivableTable().getColumnModel().getColumn(3).setResizable(false);
			mainView.getReceivableTable().getColumnModel().getColumn(3).setPreferredWidth(15);
			mainView.getReceivableTable().getColumnModel().getColumn(3).setMinWidth(0);
			mainView.getReceivableTable().getColumnModel().getColumn(3).setMaxWidth(0);
			for(Products prod: productList){
				model.addRow(new Object[]{prod.getId(),prod.getDescription(),null,null,null,null,null,null});
			}
			
			mainView.getDieselTable().getColumnModel().getSelectionModel().addListSelectionListener(new DieselTableListeners());
			mainView.getDieselTable().getSelectionModel().addListSelectionListener(new DieselTableListeners());
			mainView.getAvgasTable().getColumnModel().getSelectionModel().addListSelectionListener(new AvgasTableListeners());
			mainView.getAvgasTable().getSelectionModel().addListSelectionListener(new AvgasTableListeners());
			mainView.getKeroTable().getColumnModel().getSelectionModel().addListSelectionListener(new KeroTableListeners());
			mainView.getKeroTable().getSelectionModel().addListSelectionListener(new KeroTableListeners());
			mainView.getLubeTable().getColumnModel().getSelectionModel().addListSelectionListener(new LubeTableListeners());
			mainView.getLubeTable().getSelectionModel().addListSelectionListener(new LubeTableListeners());
			if(RoleConstant.ADMIN_ROLE != mainView.getLoggedInUser().getRoleId()){
				mainView.getJMenuBar().getMenu(2).getItem(0).setEnabled(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mainView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);	
		}
		
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	
	//Listeners
	public class AddActionListener implements ActionListener{
		MainView view = mainView;
		public void actionPerformed(ActionEvent e) {
			
			if(Util.isNotEmpty(view.getTxtInv().getText()) && Util.isNotEmpty(view.getTxtAmount().getText())&& Util.isNotEmpty(view.getTxtArName().getText())){
				if(!view.getTxtAmount().getText().matches("-?\\d+(\\.\\d+)?")){
					view.getTxtAmount().grabFocus();
					view.getLblReceivableError().setText("Invalid Amount Format");
					return;
				}
				view.getLblReceivableError().setText("");
				JTable table = view.getReceivableTable();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new Object[]{view.getTxtArName().getText(),
						view.getTxtInv().getText(),view.getTxtAmount().getText()});
				view.getTxtAmount().setText("");
				view.getTxtInv().setText("");
				view.getTxtArName().setText("");;
				calculateSalesSummary();
			}else{
				view.getLblReceivableError().setText("Incomplete input");
			}
			
		}
		
	}
	
	public class KeyPressSummaryListener implements KeyListener{

		public void keyTyped(KeyEvent e) {
			calculateSalesSummary();
			
		}

		public void keyPressed(KeyEvent e) {
			calculateSalesSummary();
			
		}

		public void keyReleased(KeyEvent e) {
			calculateSalesSummary();
			
		}
		
	}
	public class ExportDataListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				SynchData data = new SynchData(accountManager.getAllUsers(),trxManager.getAllTransactions(),productManager.getAllProduct());
				Util.exportTransaction(data);
				JOptionPane.showMessageDialog(mainView, "Successfully Exported Data");
			} catch (Exception e1) {
				Util.writeToFile("error", e1.getMessage());
				JOptionPane.showMessageDialog(mainView, e1.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		
	}
	
	public class ImportDataListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				SynchData data = Util.importTransaction();
				List<Transaction> trxList = data.getTrxList();
				productManager.synchProducts(data.getProductList());
				trxManager.synchronizeTransations(trxList,mainView.getLoggedInUser());
				JOptionPane.showMessageDialog(mainView, "Successfully imported "+trxList.size()+" Data");
			} catch (Exception e1) {
				Util.writeToFile("error", e1.getMessage());
				JOptionPane.showMessageDialog(mainView, e1.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		
	}

	
	public class SaveActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			calculateLube();
			calculateMainTable(mainView.getKeroTable(), mainView);
			calculateMainTable(mainView.getAvgasTable(), mainView);
			calculateMainTable(mainView.getDieselTable(), mainView);
			calculateSalesSummary();
			MainView view = mainView;
			try{
				Transaction transaction = new Transaction();
				transaction.getSubTransactionList().addAll(convertGasTableToSubTransaction(view.getDieselTable(),GasolineTypes.DIESEL));
				transaction.getSubTransactionList().addAll(convertGasTableToSubTransaction(view.getAvgasTable(),GasolineTypes.AVGAS));
				transaction.getSubTransactionList().addAll(convertGasTableToSubTransaction(view.getKeroTable(),GasolineTypes.KERO));
				transaction.getSubTransactionList().addAll(convertLubeTableToSubTransaction(view.getLubeTable()));
				transaction.getCollectionList().addAll(createCollections(view));
				transaction.getAccountReceivableList().addAll(createAccountreceivables(view));
				transaction.setDieselClosing(Util.toDouble(view.getTxtClosingD().getText()));
				transaction.setDieselDelivery(Util.toDouble(view.getTxtDeliveryD().getText()));
				transaction.setDieselMeter(Util.toDouble(view.getTxtMeterD().getText()));
				transaction.setDieselOpening(Util.toDouble(view.getTxtOpeningD().getText()));
				transaction.setDieselSTK(Util.toDouble(view.getTxtSTKD().getText()));
				transaction.setDieselTotal(Util.toDouble(view.getTxtTotalD().getText()));
				transaction.setDieselUgtInvt(Util.toDouble(view.getTxtUgtD().getText()));
				transaction.setTotalGas(Util.toDouble(view.getTxtTotal().getText()));
				transaction.setAvgasTotal(Util.toDouble(view.getTxtTotalA().getText()));
				transaction.setDieselOpening(Util.toDouble(view.getTxtTotalD().getText()));
				
				transaction.setAvgasClosing(Util.toDouble(view.getTxtClosingA().getText()));
				transaction.setAvgasDelivery(Util.toDouble(view.getTxtDeliveryA().getText()));
				transaction.setAvgasMeter(Util.toDouble(view.getTxtMeterA().getText()));
				transaction.setAvgasOpening(Util.toDouble(view.getTxtOpeningA().getText()));
				transaction.setAvgasSTK(Util.toDouble(view.getTxtSDKA().getText()));
				transaction.setAvgasTotal(Util.toDouble(view.getTxtTotalA().getText()));
				transaction.setAvgasUgtInvt(Util.toDouble(view.getTxtUgtA().getText()));
				transaction.setTotalSales(Util.toDouble(view.getTxtTotalSales().getText()));
				transaction.setTotalLubes(Util.toDouble(view.getTxtLubeTotal().getText()));
				transaction.setFuelSales(Util.toDouble(view.getTxtFuelSales().getText()));
				transaction.setAddLubes(Util.toDouble(view.getTxtAddLubes().getText()));
				transaction.setLessAr(Util.toDouble(view.getTxtLessAr().getText()));
				transaction.setTotalCashOnHand(Util.toDouble(view.getTxtTotalCashOnHand().getText()));
				transaction.setCashDeposited(Util.toDouble(view.getTxtCashDeposited().getText()));
				transaction.setOverShort(Util.toDouble(view.getTxtOverShort().getText()));
				transaction.setTotalCashSales(Util.toDouble(view.getTxtTotalCashSales().getText()));
				transaction.setTotalCollections(Util.toDouble(view.getTxtTotalCollection().getText()));
				transaction.setAddCollection(Util.toDouble(view.getTxtAddCollection().getText()));
				transaction.setLessExpenses(Util.toDouble(view.getTxtLessExpenses().getText()));
				if(null!=mainView.getCurrentTrx()){
					transaction.setCreatedDate(mainView.getCurrentTrx().getCreatedDate());
					transaction.setCreatedBy(mainView.getCurrentTrx().getCreatedBy());
					transaction.setUniversalTrxId(mainView.getCurrentTrx().getUniversalTrxId());
					transaction.setId(mainView.getCurrentTrx().getId());
					transaction.setUpdatedBy(mainView.getLoggedInUser().getId());
					transaction.setUpdatedDate(DatabaseUtil.getCurrentTimeStamp());
					
				}else{
					transaction.setCreatedDate(DatabaseUtil.getCurrentTimeStamp());
					transaction.setCreatedBy(mainView.getLoggedInUser().getId());
				}
				trxManager.saveUpdate(transaction);
				exportToCsv();
				mainView.setCurrentTrx(null);
				clearMainView();
				JOptionPane.showMessageDialog(view, "Saved transaction successfully");
			}catch(Exception ex){
				JOptionPane.showMessageDialog(view, ex.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				Util.writeToFile("error", ex.getMessage());
			}
		}
	}

	private void exportToCsv() throws IOException, SQLException {
		String currentDir = System.getProperty("user.dir");
		List<AccountReceivable> arList = trxManager.getallAccountReceivable();
		List<Collection> collectionList = trxManager.getallCollections();
		MainControllerHelper.exportARtoExcel(currentDir, arList);
		MainControllerHelper.exportCollectiontoExcel(currentDir, collectionList);
	}
	
	
	public void clearMainView(){
		mainView.getTxtClosingD().setText("");
		mainView.getTxtDeliveryD().setText("");
		mainView.getTxtMeterD().setText("");
		mainView.getTxtOpeningD().setText("");
		mainView.getTxtSTKD().setText("");
		mainView.getTxtTotalD().setText("");
		mainView.getTxtUgtD().setText("");
		mainView.getTxtTotal().setText("");
		mainView.getTxtTotalA().setText("");
		mainView.getTxtTotalD().setText("");
		mainView.getTxtClosingA().setText("");
		mainView.getTxtDeliveryA().setText("");
		mainView.getTxtMeterA().setText("");
		mainView.getTxtOpeningA().setText("");
		mainView.getTxtSDKA().setText("");
		mainView.getTxtTotalA().setText("");
		mainView.getTxtUgtA().setText("");
		mainView.getTxtTotalSales().setText("");
		mainView.getTxtLubeTotal().setText("");
		mainView.getTxtFuelSales().setText("");
		mainView.getTxtAddLubes().setText("");
		mainView.getTxtLessAr().setText("");
		mainView.getTxtTotalCashOnHand().setText("");
		mainView.getTxtCashDeposited().setText("");
		mainView.getTxtOverShort().setText("");
		mainView.getTxtTotalCashSales().setText("");
		mainView.getTxtTotalCollection().setText("");
		mainView.getTxtAddCollection().setText("");
		mainView.getTxtLessExpenses().setText("");
		clearTable(mainView.getAvgasTable());
		clearTable(mainView.getDieselTable());
		clearTable(mainView.getKeroTable());
		clearTableRow(mainView.getReceivableTable());
		clearTableRow(mainView.getTblCollection());
		clearLubeTable();	
	}
	
	private void clearTableRow(JTable table){
		for(int x = table.getRowCount();x>0;x--){
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.removeRow(x-1);
		}
	}
	
	private void clearTable(JTable table){
		for(int x=0;x<table.getRowCount();x++){
			for(int y=0;y<table.getColumnCount();y++){
				if(y!=0){
					table.setValueAt(null, x, y);	
				}
			}
		}
	}
	
	private void clearLubeTable(){
		JTable table = mainView.getLubeTable();
		for(int x=0;x<table.getRowCount();x++){
			for(int y=0;y<table.getColumnCount();y++){
				if(y!=0){
					table.setValueAt(null, x, y);
				}
			}
		}
	}
	
	private List<Collection> createCollections(MainView view) {
		JTable table = view.getTblCollection();
		List<Collection> collectionList = new ArrayList<Collection>();
	    for(int x=0;x<table.getRowCount();x++){
	    	Object name = table.getValueAt(x, 0);
			Object bankCheck = table.getValueAt(x, 1);
			Object amount = table.getValueAt(x, 2);
			Object id = table.getModel().getValueAt(x, 3);
			Collection collection = new Collection();
			collection.setAmount(Util.toDouble(amount));
			collection.setBankCheck(bankCheck.toString());
			collection.setCustomer(name.toString());
			collection.setId(id==null?0: Integer.valueOf(id.toString()));
			collectionList.add(collection);
	    }
		return collectionList;
	}
	
	private List<AccountReceivable> createAccountreceivables(MainView view) {
		JTable table = view.getReceivableTable();
		List<AccountReceivable> accountReceivables = new ArrayList<AccountReceivable>();
	    for(int x=0;x<table.getRowCount();x++){
	    	Object name =table.getValueAt(x, 0);
	    	String invoice = table.getValueAt(x, 1)==null?"":table.getValueAt(x, 1).toString();
	    	String amount = table.getValueAt(x, 2)==null?"0":table.getValueAt(x, 2).toString();
	    	String id = table.getValueAt(x, 3)==null?"0":table.getModel().getValueAt(x, 3).toString();
			AccountReceivable ar = new AccountReceivable();
			ar.setAmount(Util.toDouble(amount));
			ar.setInvoiceNo(invoice);
			ar.setId(Integer.valueOf(id));
			ar.setCustomer(name.toString());
			accountReceivables.add(ar);
	    }
		return accountReceivables;
	}
	
	/**
	 * @param table
	 * @param gasTypes
	 * @return
	 * @throws Exception 
	 */
	private List<SubTransaction> convertGasTableToSubTransaction(JTable table, GasolineTypes gasTypes) throws Exception {

		List<SubTransaction> subTransactions = new ArrayList<SubTransaction>();
		for(int x=0;x<table.getRowCount();x++){
			SubTransaction subtrans = new SubTransaction();
			String opening = table.getValueAt(x, 2)== null?"":table.getValueAt(x, 2).toString();
			String closing = table.getValueAt(x, 1)== null?"":table.getValueAt(x, 1).toString();
			String liters = table.getValueAt(x, 3)== null?"":table.getValueAt(x, 3).toString();
			String calib = table.getValueAt(x, 4)== null?"":table.getValueAt(x, 4).toString();
			String literSold = table.getValueAt(x, 5)== null?"":table.getValueAt(x, 5).toString();
			String price = table.getValueAt(x, 6)== null?"":table.getValueAt(x, 6).toString();
			String amount = table.getValueAt(x, 7)== null?"":table.getValueAt(x, 7).toString();
			String id = table.getValueAt(x, 8)== null?"0":table.getValueAt(x, 8).toString();
			subtrans.setProductId(gasTypes.getId());
			subtrans.setPumpNumber(x+1);
			subtrans.setAmount(Util.toDouble(amount));
			subtrans.setClosing(Util.toDouble(closing));
			subtrans.setOpening(Util.toDouble(opening));
			subtrans.setLiters(Util.toDouble(liters));
			subtrans.setCalib(Util.toDouble(calib));
			subtrans.setPrice(Util.toDouble(price));
			subtrans.setSold(Util.toDouble(literSold));
			subtrans.setId(Integer.valueOf(id));
			subTransactions.add(subtrans);
			
		}
		
		return subTransactions;
		
	}
	
	private List<SubTransaction> convertLubeTableToSubTransaction(JTable table) throws Exception{

		List<SubTransaction> subTransactions = new ArrayList<SubTransaction>();
		for(int x=0;x<table.getRowCount();x++){
			SubTransaction subtrans = new SubTransaction();
			String opening = table.getModel().getValueAt(x, 2)== null?"":table.getModel().getValueAt(x, 2).toString();
			String closing = table.getModel().getValueAt(x, 3)== null?"":table.getModel().getValueAt(x, 3).toString();
			String sold = table.getModel().getValueAt(x, 4)== null?"":table.getModel().getValueAt(x, 4).toString();
			String price = table.getModel().getValueAt(x, 5)== null?"":table.getModel().getValueAt(x, 5).toString();
			String amount = table.getModel().getValueAt(x, 6)== null?"":table.getModel().getValueAt(x, 6).toString();
			String id = table.getModel().getValueAt(x, 7)== null?"0":table.getModel().getValueAt(x, 7).toString();
			int productId = Integer.parseInt(table.getModel().getValueAt(x, 0).toString());
			
			subtrans.setId(Integer.valueOf(id));
			subtrans.setAmount(Util.toDouble(amount));
			subtrans.setClosing(Util.toDouble(closing));
			subtrans.setOpening(Util.toDouble(opening));
			subtrans.setPrice(Util.toDouble(price));
			subtrans.setLiters(0.0);
			subtrans.setCalib(0.00);
			subtrans.setPumpNumber(0);
			subtrans.setSold(Util.toDouble(sold));
			subtrans.setProductId(productId);
			subTransactions.add(subtrans);
		}
		return subTransactions;
		
	}
	
	public class AddCollectionActionListener implements ActionListener{
		MainView view = mainView;
		public void actionPerformed(ActionEvent e) {
			
			if(Util.isNotEmpty(view.getTxtBankCheck().getText()) && Util.isNotEmpty(view.getTxtAmountCollection().getText()) && Util.isNotEmpty(view.getTxtNameCollection().getText())){
				if(!view.getTxtAmountCollection().getText().matches("-?\\d+(\\.\\d+)?")){
					view.getTxtAmountCollection().grabFocus();
					view.getLblCollectionError().setText("Invalid Amount Format");
					return;
				}
				view.getLblCollectionError().setText("");
				JTable table = view.getTblCollection();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new Object[]{view.getTxtNameCollection().getText(),
						view.getTxtBankCheck().getText(),view.getTxtAmountCollection().getText()});
				view.getTxtTotalCollection().setText(String.valueOf(Util.addToDouble(view.getTxtTotalCollection().getText(), view.getTxtAmountCollection().getText())));
				view.getTxtAmountCollection().setText("");
				view.getTxtBankCheck().setText("");
				view.getTxtNameCollection().setText("");;
				calculateSalesSummary();
			}else{
				view.getLblCollectionError().setText("Incomplete input");
			}
			
		}
		
	}
	
	
    public class DieselTableListeners implements ListSelectionListener {
        MainView view = mainView;
      
		public void valueChanged(ListSelectionEvent e) {
			JTable table = view.getDieselTable();
			calculateMainTable(table,view);
			
		}
    }
    
    public class AvgasTableListeners implements ListSelectionListener {
        MainView view = mainView;
      
		public void valueChanged(ListSelectionEvent e) {
			JTable table = view.getAvgasTable();
			calculateMainTable(table,view);
		}
    }
    
    public class KeroTableListeners implements ListSelectionListener {
        MainView view = mainView;
      
		public void valueChanged(ListSelectionEvent e) {
			JTable table = view.getKeroTable();
			calculateMainTable(table, view);
        }
    
    }
    
    private void calculateMainTable(JTable table, MainView view){
    		for(int x=0;x<table.getRowCount();x++){
    			Object opening = table.getValueAt(x, 2);
    			Object closing = table.getValueAt(x, 1);
    			Object calib = table.getValueAt(x, 4);
    			Object price = table.getValueAt(x, 6);
    			Double liters = 0.000;
    			Double literSold = 0.000;
    			Double amount = 0.000;
    			
    			liters = Util.toDouble(closing)-Util.toDouble(opening);
    			table.setValueAt(liters,x, 3);
    			
    			literSold = liters - Util.toDouble(calib);
    			table.setValueAt(literSold, x, 5);
    			
    			
    			amount = literSold * Util.toDouble(price);
    			table.setValueAt(amount, x, 7);
    			
    		}
    		Double total = 0.000;
    		JTable avgasTable = view.getAvgasTable();
    		JTable dieselTable = view.getDieselTable();
    		JTable keroTable = view.getKeroTable();
    		
    		for(int x = 0; x<avgasTable.getRowCount();x++){
    			if(null != avgasTable.getValueAt(x, 7)){
    				total+=Util.toDouble(avgasTable.getValueAt(x, 7));
    			}
    		}
    		for(int x = 0; x<dieselTable.getRowCount();x++){
    			if(null != dieselTable.getValueAt(x, 7)){
    				total+=Util.toDouble(dieselTable.getValueAt(x, 7));
    			}
    		}
    		for(int x = 0; x<keroTable.getRowCount();x++){
    			if(null != keroTable.getValueAt(x, 7)){
    				total+=Util.toDouble(keroTable.getValueAt(x, 7));
    			}
    		}
    		DecimalFormat df = new DecimalFormat("#,###.####");
    		view.getTxtTotal().setText(df.format(total));	
    		
    		calculateSalesSummary();
	}
    
    public class LubeTableListeners implements ListSelectionListener {
        
		public void valueChanged(ListSelectionEvent e) {
			calculateLube();
		}
    }
    
    private void calculateSalesSummary(){
    	Double totalFuel = Util.toDouble(mainView.getTxtTotal().getText());
    	Double totalLube = Util.toDouble(mainView.getTxtLubeTotal().getText());    	
    	Double overallSales =Util.toDouble(totalLube+ totalFuel);
    	Double totalAr = calculateTotalAccountReceivable();
    	Double totalCashSale = Util.toDouble(overallSales - totalAr);
    	Double totalCollection = Util.toDouble(mainView.getTxtTotalCollection().getText());
    	Double expense = Util.toDouble(mainView.getTxtLessExpenses().getText());
    	Double cashOnHand = Util.toDouble(totalCashSale-expense+totalCollection);
    	Double deposit = Util.toDouble(mainView.getTxtCashDeposited().getText());
    	Double overShort = Util.toDouble(cashOnHand-deposit);
    	
    	mainView.getTxtAddCollection().setText(String.valueOf(totalCollection));
    	mainView.getTxtAddLubes().setText(String.valueOf(totalLube));
    	mainView.getTxtFuelSales().setText(String.valueOf(totalFuel));
    	mainView.getTxtTotalCashSales().setText(String.valueOf(totalCashSale));
    	mainView.getTxtTotalSales().setText(String.valueOf(overallSales));
    	mainView.getTxtLessAr().setText((String.valueOf(totalAr)));
    	mainView.getTxtTotalCashOnHand().setText(String.valueOf(cashOnHand));
    	mainView.getTxtOverShort().setText(String.valueOf(overShort));
    	
    }
    
    private Double calculateTotalAccountReceivable(){
    	Double totalAccountReceivable = 0.00;
    	for(int x=0;x<mainView.getReceivableTable().getRowCount();x++){
    		totalAccountReceivable+=Util.toDouble(mainView.getReceivableTable().getValueAt(x, 2));
    	}
    	return totalAccountReceivable;
    }
    
    private void calculateLube(){
    	MainView view = mainView;
    	JTable table = view.getLubeTable();
		for(int x=0; x<table.getRowCount();x++){
			Object opening = table.getValueAt(x, 1);
			Object closing = table.getValueAt(x, 2);
			Object price = table.getValueAt(x, 4);
			Double sold = 0.000;
			Double amount = 0.000;
			if(null != opening && null != closing){
				sold = Util.toDouble(opening)-Util.toDouble(closing);
				table.setValueAt(sold,x, 3);
			}
			if(sold > 0.000 && null != price){
				amount = sold * Util.toDouble(price);
				table.setValueAt(amount, x, 5);
			}
		}
		Double total = 0.000;
		JTable lubeTable = view.getLubeTable();
		
		for(int x = 0; x<lubeTable.getRowCount();x++){
			if(null != lubeTable.getValueAt(x, 5)){
				total+=Util.toDouble(lubeTable.getValueAt(x, 5));
			}
		}
		
		view.getTxtLubeTotal().setText(total.toString());
		calculateSalesSummary();
	}

        
    public class LogOutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mainView.setLoggedInUser(null);
			mainView.setCurrentTrx(null);
			mainView.dispose();
			openLogInView();
			
		}
    	
    }
    public class InventoryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			openInventoryView();
			
		}
    	
    }
    public class LoadTransactionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				clearMainView();
				new TransactionListView(mainView);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(mainView, e1.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				Util.writeToFile("error",e1.getMessage());
			}
		}
    }
    
    public class NewTransactionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
				clearMainView();
				mainView.setCurrentTrx(null);
		}
    }
    

    public class DeleteArActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel) mainView.getReceivableTable().getModel();
			model.removeRow(mainView.getReceivableTable().getSelectedRow());
			calculateSalesSummary();
		}
     
    }
    
    public class DeleteCollectionActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel) mainView.getTblCollection().getModel();
			Double amount = Util.toDouble(mainView.getTblCollection().getValueAt(mainView.getTblCollection().getSelectedRow(),2));
			Double total = Util.toDouble(mainView.getTxtTotalCollection().getText());
			Double finalAmount = total-amount;
			mainView.getTxtTotalCollection().setText(String.valueOf(finalAmount));
			model.removeRow(mainView.getTblCollection().getSelectedRow());
			calculateSalesSummary();
			
		}
    } 
   
    public class RegisterActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				new RegisterView(mainView);
			} catch (SQLException e1) {
				Util.writeToFile("error",e1.getMessage());
			}
		}    	
    }
    
    private void openLogInView(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginView();
				} catch (Exception e) {
					Util.writeToFile("error", e.getMessage());
					JOptionPane.showMessageDialog(mainView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
    private void openInventoryView(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InventoryView();
				} catch (Exception e) {
					Util.writeToFile("error", e.getMessage());
					JOptionPane.showMessageDialog(mainView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
  }


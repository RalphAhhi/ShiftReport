package com.fv.shiftreport.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.fv.shiftreport.api.RXTable;
import com.fv.shiftreport.controller.MainController;
import com.fv.shiftreport.model.Transaction;
import com.fv.shiftreport.model.UserResponse;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserResponse loggedInUser;
	private MainController controller;
	private RXTable dieselTable;
	private RXTable avgasTable;
	private RXTable keroTable;
	private JTextField txtTotal;
	private JTextField txtUgtD;
	private JTextField txtUgtA;
	private JTextField txtOpeningD;
	private JTextField txtOpeningA;
	private JTextField txtDeliveryD;
	private JTextField txtDeliveryA;
	private JTextField txtTotalD;
	private JTextField txtTotalA;
	private JTextField txtClosingD;
	private JTextField txtClosingA;
	private JTextField txtSTKD;
	private JTextField txtSDKA;
	private JTextField txtMeterD;
	private JTextField txtMeterA;
	private RXTable lubeTable;
	private RXTable receivableTable;
	private JTextField txtInv;
	private JTextField txtAmount;
	private JButton btnAdd;
	private JButton btnDelete;
	private JTextField txtArName;
	private JLabel lblReceivableError;
	private JLabel txtName;
	private JTextField txtBankCheck;
	private JTextField txtAmountCollection;
	private RXTable tblCollection;
	private JButton btnAddCollection;
	private JButton btnDeleteCollection;
	private JTextField txtNameCollection;
	private JLabel lblCollectionError;
	private JTextField txtLubeTotal;
	private JTextField txtFuelSales;
	private JTextField txtAddLubes;
	private JTextField txtTotalSales;
	private JTextField txtLessAr;
	private JTextField txtTotalCashOnHand;
	private JTextField txtCashDeposited;
	private JTextField txtOverShort;
	private JTextField txtTotalCashSales;
	private JTextField txtAddCollection;
	private JTextField txtLessExpenses;
	private JButton btnSave;
	private JTextField txtTotalCollection;
	private JMenuBar menuBar;
	private Transaction currentTrx;
	

	/**
	 * Create the application.
	 */
	public MainView(UserResponse user) {
		this.loggedInUser = user;
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		new Dimension ( ss.width-100, ss.height+600 );
		getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		
		JPanel trxTab = new JPanel();
		trxTab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tabbedPane.addTab("Transaction", null, trxTab, null);
		
		trxTab.setPreferredSize(new Dimension(1266, 1398));
		trxTab.setLayout(null);
		
		JPanel dieselPanel = new JPanel();
		dieselPanel.setBorder(new TitledBorder(null, "Diesel", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		dieselPanel.setBounds(10, 27, ss.width-50, 147);
		trxTab.add(dieselPanel);
		dieselPanel.setLayout(null);
		
		JScrollPane dieselScroll = new JScrollPane();
		dieselScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		dieselScroll.setBounds(20, 26, ss.width - 100, 99);
		dieselPanel.add(dieselScroll);
		
		dieselTable = new RXTable();
		dieselTable.setRowSelectionAllowed(false);
		dieselTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dieselTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dieselTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dieselTable.setCellSelectionEnabled(true);
		dieselTable.setAutoscrolls(false);
		dieselTable.setSelectAllForEdit(true);
		dieselTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount", "id"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, true, true, false, true, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		dieselTable.getColumnModel().getColumn(0).setResizable(false);
		dieselTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		dieselTable.getColumnModel().getColumn(8).setResizable(false);
		dieselTable.getColumnModel().getColumn(8).setPreferredWidth(15);
		dieselTable.getColumnModel().getColumn(8).setMinWidth(0);
		dieselTable.getColumnModel().getColumn(8).setMaxWidth(0);
		dieselTable.setRowHeight(25);
		
		dieselScroll.setViewportView(dieselTable);
		
		JPanel avgasPanel = new JPanel();
		avgasPanel.setBorder(new TitledBorder(null, "Avgas", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		avgasPanel.setBounds(10, 180, ss.width-50, 147);
		trxTab.add(avgasPanel);
		avgasPanel.setLayout(null);
		
		JScrollPane avgasScroll = new JScrollPane();
		avgasScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		avgasScroll.setBounds(25, 24, ss.width-100, 99);
		avgasPanel.add(avgasScroll);
		
		avgasTable = new RXTable();
		avgasTable.setSelectAllForEdit(true);
		avgasTable.setRowSelectionAllowed(false);
		avgasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		avgasTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgasTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount", "id"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, false, true, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		} );
		avgasTable.getColumnModel().getColumn(0).setResizable(false);
		avgasTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		avgasTable.getColumnModel().getColumn(8).setResizable(false);
		avgasTable.getColumnModel().getColumn(8).setPreferredWidth(0);
		avgasTable.getColumnModel().getColumn(8).setMinWidth(0);
		avgasTable.getColumnModel().getColumn(8).setMaxWidth(0);
		avgasTable.setRowHeight(25);
		avgasScroll.setViewportView(avgasTable);
		
		JPanel keroPanel = new JPanel();
		keroPanel.setLayout(null);
		keroPanel.setBorder(new TitledBorder(null, "Kero", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		keroPanel.setBounds(10, 340, ss.width-50, 93);
		trxTab.add(keroPanel);
		
		JScrollPane keroScroll = new JScrollPane();
		keroScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		keroScroll.setBounds(25, 24, ss.width-100, 47);
		keroPanel.add(keroScroll);
		
		keroTable = new RXTable();
		keroTable.setSelectAllForEdit(true);
		keroTable.setRowSelectionAllowed(false);
		keroTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		keroTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		keroTable.setRowHeight(25);
		keroTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount", "id"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Object.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, true, true, false, true, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		keroTable.getColumnModel().getColumn(0).setResizable(false);
		keroTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		keroTable.getColumnModel().getColumn(8).setResizable(false);
		keroTable.getColumnModel().getColumn(8).setPreferredWidth(0);
		keroTable.getColumnModel().getColumn(8).setMinWidth(0);
		keroTable.getColumnModel().getColumn(8).setMaxWidth(0);
		keroScroll.setViewportView(keroTable);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(40, 444, 46, 14);
		trxTab.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setDisabledTextColor(Color.BLACK);
		txtTotal.setBounds(91, 442, 176, 20);
		trxTab.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEnabled(false);
		
		JPanel summaryLubePanel = new JPanel();
		summaryLubePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		summaryLubePanel.setBounds(10, 476, 652, 895);
		trxTab.add(summaryLubePanel);
		summaryLubePanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 11, 497, 240);
		summaryLubePanel.add(panel);
		panel.setLayout(new GridLayout(9, 2, 10, 5));
		
		JLabel lblNewLabel = new JLabel("Diesel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Avgas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		txtUgtD = new JTextField();
		panel.add(txtUgtD);
		txtUgtD.setColumns(10);
		
		txtUgtA = new JTextField();
		panel.add(txtUgtA);
		txtUgtA.setColumns(10);
		
		txtOpeningD = new JTextField();
		panel.add(txtOpeningD);
		txtOpeningD.setColumns(10);
		
		txtOpeningA = new JTextField();
		panel.add(txtOpeningA);
		txtOpeningA.setColumns(10);
		
		txtDeliveryD = new JTextField();
		panel.add(txtDeliveryD);
		txtDeliveryD.setColumns(10);
		
		txtDeliveryA = new JTextField();
		panel.add(txtDeliveryA);
		txtDeliveryA.setColumns(10);
		
		txtTotalD = new JTextField();
		panel.add(txtTotalD);
		txtTotalD.setColumns(10);
		
		txtTotalA = new JTextField();
		panel.add(txtTotalA);
		txtTotalA.setColumns(10);
		
		txtClosingD = new JTextField();
		panel.add(txtClosingD);
		txtClosingD.setColumns(10);
		
		txtClosingA = new JTextField();
		panel.add(txtClosingA);
		txtClosingA.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		txtSTKD = new JTextField();
		panel.add(txtSTKD);
		txtSTKD.setColumns(10);
		
		txtSDKA = new JTextField();
		panel.add(txtSDKA);
		txtSDKA.setColumns(10);
		
		txtMeterD = new JTextField();
		panel.add(txtMeterD);
		txtMeterD.setColumns(10);
		
		txtMeterA = new JTextField();
		panel.add(txtMeterA);
		txtMeterA.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 70, 240);
		summaryLubePanel.add(panel_2);
		panel_2.setLayout(new GridLayout(9, 1, 0, 5));
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ugt Invt");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_10 = new JLabel("Opening");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Delivery");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Closing");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("STK");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_3 = new JLabel("METER");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 300, 630, 163);
		summaryLubePanel.add(scrollPane_1);
		
		lubeTable = new RXTable();
		lubeTable.setSelectAllForEdit(true);
		lubeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lubeTable.setRowSelectionAllowed(false);
		lubeTable.setColumnSelectionAllowed(true);
		lubeTable.setCellSelectionEnabled(true);
		lubeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProductId", "Lubes", "Open", "Close", "Sold", "Price", "Amount", "Id"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Integer.class, Integer.class, Integer.class, Double.class, Double.class,Long.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, false, true, false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		lubeTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lubeTable.setBounds(0, 0, 1, 1);
		scrollPane_1.setViewportView(lubeTable);
		
		JLabel lblNewLabel_21 = new JLabel("TOTAL:");
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_21.setForeground(Color.BLACK);
		lblNewLabel_21.setBounds(30, 483, 46, 14);
		summaryLubePanel.add(lblNewLabel_21);
		
		txtLubeTotal = new JTextField();
		txtLubeTotal.setDisabledTextColor(Color.BLACK);
		txtLubeTotal.setEnabled(false);
		txtLubeTotal.setBounds(78, 480, 176, 20);
		summaryLubePanel.add(txtLubeTotal);
		txtLubeTotal.setColumns(10);
		
		JPanel saleSummaryPanel = new JPanel();
		saleSummaryPanel.setBounds(10, 510, 630, 380);
		summaryLubePanel.add(saleSummaryPanel);
		saleSummaryPanel.setBorder(new TitledBorder(null, "Sales Summary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		saleSummaryPanel.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(210, 20, 200, 310);
		saleSummaryPanel.add(panel_8);
		panel_8.setLayout(new GridLayout(10, 1, 10, 10));
		
		txtFuelSales = new JTextField();
		txtFuelSales.setEditable(false);
		panel_8.add(txtFuelSales);
		txtFuelSales.setColumns(10);
		
		txtAddLubes = new JTextField();
		txtAddLubes.setEditable(false);
		panel_8.add(txtAddLubes);
		txtAddLubes.setColumns(10);
		
		txtTotalSales = new JTextField();
		txtTotalSales.setEditable(false);
		panel_8.add(txtTotalSales);
		txtTotalSales.setColumns(10);
		
		txtLessAr = new JTextField();
		txtLessAr.setEditable(false);
		panel_8.add(txtLessAr);
		txtLessAr.setColumns(10);
		
		txtTotalCashSales = new JTextField();
		txtTotalCashSales.setEditable(false);
		txtTotalCashSales.setText("");
		panel_8.add(txtTotalCashSales);
		txtTotalCashSales.setColumns(10);
		
		txtAddCollection = new JTextField();
		txtAddCollection.setEditable(false);
		panel_8.add(txtAddCollection);
		txtAddCollection.setColumns(10);
		
		txtLessExpenses = new JTextField();
		panel_8.add(txtLessExpenses);
		txtLessExpenses.setColumns(10);
		
		txtTotalCashOnHand = new JTextField();
		txtTotalCashOnHand.setEditable(false);
		panel_8.add(txtTotalCashOnHand);
		txtTotalCashOnHand.setColumns(10);
		
		txtCashDeposited = new JTextField();
		panel_8.add(txtCashDeposited);
		txtCashDeposited.setColumns(10);
		
		txtOverShort = new JTextField();
		txtOverShort.setEditable(false);
		panel_8.add(txtOverShort);
		txtOverShort.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(40, 20, 154, 310);
		saleSummaryPanel.add(panel_7);
		panel_7.setLayout(new GridLayout(10, 1, 10, 20));
		
		JLabel lblNewLabel_28 = new JLabel("Fuel Sales:");
		panel_7.add(lblNewLabel_28);
		
		JLabel lblNewLabel_27 = new JLabel("Add Lubes:");
		panel_7.add(lblNewLabel_27);
		
		JLabel lblNewLabel_22 = new JLabel("Total Sales:");
		panel_7.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("Less A/R");
		panel_7.add(lblNewLabel_23);
		
		JLabel lblNewLabel_25 = new JLabel("Total Cash Sales:");
		panel_7.add(lblNewLabel_25);
		
		JLabel lblNewLabel_24 = new JLabel("Add Collection:");
		panel_7.add(lblNewLabel_24);
		
		JLabel lblNewLabel_30 = new JLabel("Less Expenses:");
		panel_7.add(lblNewLabel_30);
		
		JLabel lblNewLabel_29 = new JLabel("Total Cash on Hand:");
		panel_7.add(lblNewLabel_29);
		
		JLabel lblNewLabel_26 = new JLabel("Cash Deposited:");
		panel_7.add(lblNewLabel_26);
		
		JLabel lblNewLabel_31 = new JLabel("Over/Short:");
		panel_7.add(lblNewLabel_31);
		
		JPanel accountReceivablePanel = new JPanel();
		accountReceivablePanel.setBorder(new TitledBorder(null, "ACCOUNT RECEIVABLES", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		accountReceivablePanel.setBounds(732, 476, 523, 433);
		trxTab.add(accountReceivablePanel);
		accountReceivablePanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 113, 503, 309);
		accountReceivablePanel.add(scrollPane_2);
		
		receivableTable = new RXTable();
		receivableTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		receivableTable.setRowHeight(20);
		receivableTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Inv. No.", "Amount","Id"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		receivableTable.getColumnModel().getColumn(0).setResizable(false);
		receivableTable.getColumnModel().getColumn(1).setResizable(false);
		receivableTable.getColumnModel().getColumn(2).setResizable(false);
		scrollPane_2.setViewportView(receivableTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 21, 503, 45);
		accountReceivablePanel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 3, 20, 0));
		
		JLabel lblNewLabel_13 = new JLabel("Name");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_12 = new JLabel("Inv. No.");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_12);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		txtArName = new JTextField();
		panel_1.add(txtArName);
		
		
		txtInv = new JTextField();
		txtInv.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtInv);
		txtInv.setColumns(10);
		
		txtAmount = new JTextField();
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtAmount);
		txtAmount.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 79, 65, 23);
		accountReceivablePanel.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(74, 79, 71, 23);
		accountReceivablePanel.add(btnDelete);
		
		lblReceivableError = new JLabel("");
		lblReceivableError.setForeground(Color.RED);
		lblReceivableError.setBounds(160, 77, 217, 25);
		accountReceivablePanel.add(lblReceivableError);
		
		JLabel lblNewLabel_14 = new JLabel("HI:");
		lblNewLabel_14.setBounds(81, 11, 46, 14);
		trxTab.add(lblNewLabel_14);
		
		txtName = new JLabel("");
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(102, 11, 185, 14);
		trxTab.add(txtName);
		
		JPanel collectionPanel = new JPanel();
		collectionPanel.setBorder(new TitledBorder(null, "COLLECTIONS", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		collectionPanel.setBounds(732, 909, 523, 460);
		trxTab.add(collectionPanel);
		collectionPanel.setLayout(null);
		
		btnAddCollection = new JButton("Add");
		btnAddCollection.setBounds(10, 79, 65, 23);
		collectionPanel.add(btnAddCollection);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 21, 503, 45);
		collectionPanel.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 3, 20, 0));
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblName);
		
		JLabel lblNewLabel_16 = new JLabel("Banks/Chk#");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Amount");
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_17);
		
		txtNameCollection = new JTextField();
		panel_5.add(txtNameCollection);
		
		txtBankCheck = new JTextField();
		panel_5.add(txtBankCheck);
		txtBankCheck.setColumns(10);
		
		txtAmountCollection = new JTextField();
		panel_5.add(txtAmountCollection);
		txtAmountCollection.setColumns(10);
		
		btnDeleteCollection = new JButton("Delete");
		btnDeleteCollection.setBounds(74, 79, 71, 23);
		collectionPanel.add(btnDeleteCollection);
		
		JLabel lblCollectionTotal = new JLabel("Total Collection :");
		lblCollectionTotal.setBounds(285, 430, 110, 22);
		collectionPanel.add(lblCollectionTotal);
		
		txtTotalCollection = new JTextField();
		txtTotalCollection.setEditable(false);
		txtTotalCollection.setBounds(384, 430, 130, 22);
		collectionPanel.add(txtTotalCollection);
		txtTotalCollection.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 113, 503, 309);
		collectionPanel.add(scrollPane_3);
		
		tblCollection = new RXTable();
		tblCollection.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Banks/Chks#", "Amount", "id"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCollection.getColumnModel().getColumn(0).setResizable(false);
		tblCollection.getColumnModel().getColumn(1).setResizable(false);
		tblCollection.getColumnModel().getColumn(2).setResizable(false);
		tblCollection.setBounds(0, 0, 1, 1);
		scrollPane_3.setViewportView(tblCollection);
		
		lblCollectionError = new JLabel("");
		lblCollectionError.setForeground(Color.RED);
		lblCollectionError.setBounds(160, 77, 217, 25);
		collectionPanel.add(lblCollectionError);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(1142, 1375, 89, 23);
		trxTab.add(btnSave);
		
		menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadTransaction = new JMenuItem("Load Transaction");
		mnFile.add(mntmLoadTransaction);
		
		JMenuItem mntmNewTransaction = new JMenuItem("New Transaction");
		mnFile.add(mntmNewTransaction);
		
		JMenuItem mntmRegister = new JMenuItem("Register");
		mnFile.add(mntmRegister);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Logout");
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mnmExport = new JMenuItem("Export Data");
		mnTools.add(mnmExport);
		
		JMenuItem mnmImport = new JMenuItem("Import Data");
		mnTools.add(mnmImport);
		
		JMenuItem mnmSynchNetwork = new JMenuItem("Synch Network");
		mnTools.add(mnmSynchNetwork);
		
		JMenu mnInventory = new JMenu("Products");
		menuBar.add(mnInventory);
		
		JMenuItem mnmInventory = new JMenuItem("Inventory");
		mnInventory.add(mnmInventory);
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller = new MainController();
		controller.setMainView(this);
		controller.init();
		
		
	}

	public UserResponse getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserResponse loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public MainController getController() {
		return controller;
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

	public RXTable getDieselTable() {
		return dieselTable;
	}

	public void setDieselTable(RXTable dieselTable) {
		this.dieselTable = dieselTable;
	}

	public RXTable getAvgasTable() {
		return avgasTable;
	}

	public void setAvgasTable(RXTable avgasTable) {
		this.avgasTable = avgasTable;
	}

	public RXTable getKeroTable() {
		return keroTable;
	}

	public void setKeroTable(RXTable keroTable) {
		this.keroTable = keroTable;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JTextField getTxtUgtD() {
		return txtUgtD;
	}

	public void setTxtUgtD(JTextField txtUgtD) {
		this.txtUgtD = txtUgtD;
	}

	public JTextField getTxtUgtA() {
		return txtUgtA;
	}

	public void setTxtUgtA(JTextField txtUgtA) {
		this.txtUgtA = txtUgtA;
	}

	public JTextField getTxtOpeningD() {
		return txtOpeningD;
	}

	public void setTxtOpeningD(JTextField txtOpeningD) {
		this.txtOpeningD = txtOpeningD;
	}

	public JTextField getTxtOpeningA() {
		return txtOpeningA;
	}

	public void setTxtOpeningA(JTextField txtOpeningA) {
		this.txtOpeningA = txtOpeningA;
	}

	public JTextField getTxtDeliveryD() {
		return txtDeliveryD;
	}

	public void setTxtDeliveryD(JTextField txtDeliveryD) {
		this.txtDeliveryD = txtDeliveryD;
	}

	public JTextField getTxtDeliveryA() {
		return txtDeliveryA;
	}

	public void setTxtDeliveryA(JTextField txtDeliveryA) {
		this.txtDeliveryA = txtDeliveryA;
	}

	public JTextField getTxtTotalD() {
		return txtTotalD;
	}

	public void setTxtTotalD(JTextField txtTotalD) {
		this.txtTotalD = txtTotalD;
	}

	public JTextField getTxtTotalA() {
		return txtTotalA;
	}

	public void setTxtTotalA(JTextField txtTotalA) {
		this.txtTotalA = txtTotalA;
	}

	public JTextField getTxtClosingD() {
		return txtClosingD;
	}

	public void setTxtClosingD(JTextField txtClosingD) {
		this.txtClosingD = txtClosingD;
	}

	public JTextField getTxtClosingA() {
		return txtClosingA;
	}

	public void setTxtClosingA(JTextField txtClosingA) {
		this.txtClosingA = txtClosingA;
	}

	public JTextField getTxtSTKD() {
		return txtSTKD;
	}

	public void setTxtSTKD(JTextField txtSTKD) {
		this.txtSTKD = txtSTKD;
	}

	public JTextField getTxtSDKA() {
		return txtSDKA;
	}

	public void setTxtSDKA(JTextField txtSDKA) {
		this.txtSDKA = txtSDKA;
	}

	public JTextField getTxtMeterD() {
		return txtMeterD;
	}

	public void setTxtMeterD(JTextField txtMeterD) {
		this.txtMeterD = txtMeterD;
	}

	public JTextField getTxtMeterA() {
		return txtMeterA;
	}

	public void setTxtMeterA(JTextField txtMeterA) {
		this.txtMeterA = txtMeterA;
	}

	public RXTable getLubeTable() {
		return lubeTable;
	}

	public void setLubeTable(RXTable lubeTable) {
		this.lubeTable = lubeTable;
	}

	public RXTable getReceivableTable() {
		return receivableTable;
	}

	public void setReceivableTable(RXTable receivableTable) {
		this.receivableTable = receivableTable;
	}

	public JTextField getTxtInv() {
		return txtInv;
	}

	public void setTxtInv(JTextField txtInv) {
		this.txtInv = txtInv;
	}

	public JTextField getTxtAmount() {
		return txtAmount;
	}

	public void setTxtAmount(JTextField txtAmount) {
		this.txtAmount = txtAmount;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JTextField getTxtArName() {
		return txtArName;
	}

	public JLabel getLblReceivableError() {
		return lblReceivableError;
	}

	public void setLblReceivableError(JLabel lblReceivableError) {
		this.lblReceivableError = lblReceivableError;
	}

	public JLabel getTxtName() {
		return txtName;
	}

	public void setTxtName(JLabel txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtBankCheck() {
		return txtBankCheck;
	}

	public void setTxtBankCheck(JTextField txtBankCheck) {
		this.txtBankCheck = txtBankCheck;
	}

	public JTextField getTxtAmountCollection() {
		return txtAmountCollection;
	}

	public void setTxtAmountCollection(JTextField txtAmountCollection) {
		this.txtAmountCollection = txtAmountCollection;
	}

	public RXTable getTblCollection() {
		return tblCollection;
	}

	public void setTblCollection(RXTable tblCollection) {
		this.tblCollection = tblCollection;
	}

	public JButton getBtnAddCollection() {
		return btnAddCollection;
	}

	public void setBtnAddCollection(JButton btnAddCollection) {
		this.btnAddCollection = btnAddCollection;
	}

	public JButton getBtnDeleteCollection() {
		return btnDeleteCollection;
	}

	public void setBtnDeleteCollection(JButton btnDeleteCollection) {
		this.btnDeleteCollection = btnDeleteCollection;
	}

	public JTextField getTxtNameCollection() {
		return txtNameCollection;
	}

	public void setTxtNameCollection(JTextField txtNameCollection) {
		this.txtNameCollection = txtNameCollection;
	}

	public JLabel getLblCollectionError() {
		return lblCollectionError;
	}

	public void setLblCollectionError(JLabel lblCollectionError) {
		this.lblCollectionError = lblCollectionError;
	}

	public JTextField getTxtLubeTotal() {
		return txtLubeTotal;
	}

	public void setTxtLubeTotal(JTextField txtLubeTotal) {
		this.txtLubeTotal = txtLubeTotal;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JTextField getTxtFuelSales() {
		return txtFuelSales;
	}

	public void setTxtFuelSales(JTextField txtFuelSales) {
		this.txtFuelSales = txtFuelSales;
	}

	public JTextField getTxtAddLubes() {
		return txtAddLubes;
	}

	public void setTxtAddLubes(JTextField txtAddLubes) {
		this.txtAddLubes = txtAddLubes;
	}

	public JTextField getTxtTotalSales() {
		return txtTotalSales;
	}

	public void setTxtTotalSales(JTextField txtTotalSales) {
		this.txtTotalSales = txtTotalSales;
	}

	public JTextField getTxtLessAr() {
		return txtLessAr;
	}

	public void setTxtLessAr(JTextField txtLessAr) {
		this.txtLessAr = txtLessAr;
	}

	public JTextField getTxtTotalCashOnHand() {
		return txtTotalCashOnHand;
	}

	public void setTxtTotalCashOnHand(JTextField txtTotalCashOnHand) {
		this.txtTotalCashOnHand = txtTotalCashOnHand;
	}

	public JTextField getTxtCashDeposited() {
		return txtCashDeposited;
	}

	public void setTxtCashDeposited(JTextField txtCashDeposited) {
		this.txtCashDeposited = txtCashDeposited;
	}

	public JTextField getTxtOverShort() {
		return txtOverShort;
	}

	public void setTxtOverShort(JTextField txtOverShort) {
		this.txtOverShort = txtOverShort;
	}

	public JTextField getTxtTotalCashSales() {
		return txtTotalCashSales;
	}

	public void setTxtTotalCashSales(JTextField txtTotalCashSales) {
		this.txtTotalCashSales = txtTotalCashSales;
	}

	public JTextField getTxtAddCollection() {
		return txtAddCollection;
	}

	public void setTxtAddCollection(JTextField txtAddCollection) {
		this.txtAddCollection = txtAddCollection;
	}

	public JTextField getTxtLessExpenses() {
		return txtLessExpenses;
	}

	public void setTxtLessExpenses(JTextField txtLessExpenses) {
		this.txtLessExpenses = txtLessExpenses;
	}

	public JTextField getTxtTotalCollection() {
		return txtTotalCollection;
	}

	public void setTxtTotalCollection(JTextField txtTotalCollection) {
		this.txtTotalCollection = txtTotalCollection;
	}

	public JMenuBar getJMenuBar() {
		return menuBar;
	}

	public void setJMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Transaction getCurrentTrx() {
		return currentTrx;
	}

	public void setCurrentTrx(Transaction currentTrx) {
		this.currentTrx = currentTrx;
	}
}

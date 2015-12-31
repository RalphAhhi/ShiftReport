package com.fv.shiftreport.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.fv.shiftreport.controller.MainController;
import com.fv.shiftreport.model.UserResponse;
import javax.swing.border.CompoundBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListSelectionModel;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserResponse loggedInUser;
	private MainController controller;
	private JTable dieselTable;
	private JTable avgasTable;
	private JTable keroTable;
	private JTextField txtTotal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	

	/**
	 * Create the application.
	 */
	public MainView(UserResponse user) {
		this.loggedInUser = user;
		initialize();
		controller = new MainController();
		controller.setMainView(this);
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( ss.width-100, ss.height+500 );
		getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		
		JPanel trxPanel = new JPanel();
		trxPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tabbedPane.addTab("Transaction", null, trxPanel, null);
		
		trxPanel.setPreferredSize(frameSize);
		trxPanel.setLayout(null);
		
		JPanel dieselPanel = new JPanel();
		dieselPanel.setBorder(new TitledBorder(null, "Diesel", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		dieselPanel.setBounds(10, 27, ss.width-50, 147);
		trxPanel.add(dieselPanel);
		dieselPanel.setLayout(null);
		
		JScrollPane dieselScroll = new JScrollPane();
		dieselScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		dieselScroll.setBounds(20, 26, ss.width - 100, 99);
		dieselPanel.add(dieselScroll);
		
		dieselTable = new JTable();
		dieselTable.setRowSelectionAllowed(false);
		dieselTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dieselTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dieselTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dieselTable.setCellSelectionEnabled(true);
		dieselTable.setAutoscrolls(false);
		dieselTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		dieselTable.getColumnModel().getColumn(0).setResizable(false);
		dieselTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		dieselTable.setRowHeight(25);
		
		dieselScroll.setViewportView(dieselTable);
		
		JPanel avgasPanel = new JPanel();
		avgasPanel.setBorder(new TitledBorder(null, "Avgas", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		avgasPanel.setBounds(10, 180, ss.width-50, 147);
		trxPanel.add(avgasPanel);
		avgasPanel.setLayout(null);
		
		JScrollPane avgasScroll = new JScrollPane();
		avgasScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		avgasScroll.setBounds(25, 24, ss.width-100, 99);
		avgasPanel.add(avgasScroll);
		
		avgasTable = new JTable();
		avgasTable.setRowSelectionAllowed(false);
		avgasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		avgasTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		avgasTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null},
				{"3", null, "", null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		avgasTable.getColumnModel().getColumn(0).setResizable(false);
		avgasTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		avgasTable.setRowHeight(25);
		avgasScroll.setViewportView(avgasTable);
		
		JPanel keroPanel = new JPanel();
		keroPanel.setLayout(null);
		keroPanel.setBorder(new TitledBorder(null, "Kero", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		keroPanel.setBounds(10, 340, ss.width-50, 93);
		trxPanel.add(keroPanel);
		
		JScrollPane keroScroll = new JScrollPane();
		keroScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		keroScroll.setBounds(25, 24, ss.width-100, 47);
		keroPanel.add(keroScroll);
		
		keroTable = new JTable();
		keroTable.setRowSelectionAllowed(false);
		keroTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		keroTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		keroTable.setRowHeight(25);
		keroTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null},
			},
			new String[] {
				"Pump No.", "Closing", "Opening", "Liters", "Calib", "Ltr. Sold", "Price/ltr", "Amount"
			}
		));
		keroTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		keroScroll.setViewportView(keroTable);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(40, 444, 46, 14);
		trxPanel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(91, 442, 176, 20);
		trxPanel.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 476, 573, 270);
		trxPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(110, 11, 390, 240);
		panel_1.add(panel);
		panel.setLayout(new GridLayout(9, 2, 10, 5));
		
		JLabel lblNewLabel = new JLabel("Diesel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Avgas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		textField_10 = new JTextField();
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		panel.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		panel.add(textField_13);
		textField_13.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 90, 240);
		panel_1.add(panel_2);
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
		
		
		
		
	
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
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
}

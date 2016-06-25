package com.fv.shiftreport.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.fv.shiftreport.controller.TransactionListController;
import com.fv.shiftreport.dao.TransactionDaoImpl;
import com.fv.shiftreport.dao.UserDaoImpl;
import com.fv.shiftreport.manager.AccountManagerImpl;
import com.fv.shiftreport.manager.TransactionManagerImpl;

public class TransactionListView extends JDialog {
	
	TransactionListController controller = new TransactionListController();
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnLoad;
	private JTable tblTrxList;

	public TransactionListView(MainView mainView) throws SQLException {
		init();
		controller.setView(this);
		controller.setTransactionManager(new TransactionManagerImpl());
		controller.getTransactionManager().setTransactionDao(new TransactionDaoImpl());
		controller.setAccountManager(new AccountManagerImpl());
		controller.getAccountManager().setUserDao(new UserDaoImpl());
		controller.init(mainView);
	}
	
	public void init(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(TransactionListView.class.getResource("/images/fvlogo.jpg")));
		setResizable(false);
		
		setVisible(true);
		
		setTitle("Load Transaction");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Transaction List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 11, 625, 261);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tblTrxList = new JTable();
		tblTrxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTrxList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TransactionId", "CreatedBy", "CreatedDate", "UpdatedBy", "UpdatedDate"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblTrxList.getColumnModel().getColumn(0).setPreferredWidth(96);
		scrollPane.setViewportView(tblTrxList);
		
		btnLoad = new JButton("Load Transaction");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoad.setBounds(478, 284, 159, 35);
		getContentPane().add(btnLoad);
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 350, 300 );
		this.setBounds ( ss.width / 2 - frameSize.width / 2, 
		                  ss.height / 2 - frameSize.height / 2,
		                  666, 373 );
	}
	

	public JButton getBtnRegister() {
		return btnLoad;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnLoad = btnRegister;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}

	public void setBtnLoad(JButton btnLoad) {
		this.btnLoad = btnLoad;
	}

	public JTable getTblTrxList() {
		return tblTrxList;
	}

	public void setTblTrxList(JTable tblTrxList) {
		this.tblTrxList = tblTrxList;
	}
}

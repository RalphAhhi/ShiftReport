package com.fv.shiftreport.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.fv.shiftreport.controller.InventoryController;
import com.fv.shiftreport.dao.ProductDaoImpl;
import com.fv.shiftreport.manager.ProductManagerImpl;
import com.fv.shiftreport.model.Products;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;

public class InventoryView extends JDialog {
	
	InventoryController controller = new InventoryController();
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JList<Products> plist;

	public InventoryView() throws SQLException {
		init();
		controller.setView(this);
		controller.setProductManager(new ProductManagerImpl());
		controller.getProductManager().setProductDao(new ProductDaoImpl());
		controller.init();
	}
	
	public void init(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(InventoryView.class.getResource("/images/fvlogo.jpg")));
		setResizable(false);
		
		setVisible(true);
		
		setTitle("Inventory");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Products", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 210, 322);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		plist = new JList();
		panel.add(plist, BorderLayout.CENTER);
		
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 350, 300 );
		this.setBounds ( ss.width / 2 - frameSize.width / 2, 
		                  ss.height / 2 - frameSize.height / 2,
		                  550, 373 );
	}

	public JList<Products> getPlist() {
		return plist;
	}

	public void setPlist(JList<Products> plist) {
		this.plist = plist;
	}
}

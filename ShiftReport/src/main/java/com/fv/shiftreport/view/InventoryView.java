package com.fv.shiftreport.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.fv.shiftreport.controller.InventoryController;
import com.fv.shiftreport.dao.ProductDaoImpl;
import com.fv.shiftreport.manager.ProductManagerImpl;
import com.fv.shiftreport.model.Products;

public class InventoryView extends JDialog {
	
	InventoryController controller = new InventoryController();
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JList<Products> plist;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel_2;

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
		setAlwaysOnTop(true);
		setVisible(true);
		
		setTitle("Inventory");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Products", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 210, 422);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		plist = new JList();
		panel.add(plist, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(230, 11, 404, 218);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBounds(230, 241, 404, 23);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 10, 0));
		
		JLabel label = new JLabel("");
		panel_2.add(label);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);
		
		JButton btnSave = new JButton("Save");
		panel_2.add(btnSave);
		
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 350, 300 );
		this.setBounds ( ss.width / 4 - frameSize.width / 4, 
		                  ss.height / 4 - frameSize.height / 4,
		                  671, 501 );
	}

	public JList<Products> getPlist() {
		return plist;
	}

	public void setPlist(JList<Products> plist) {
		this.plist = plist;
	}
}

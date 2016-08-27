package com.fv.shiftreport.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.fv.shiftreport.controller.RegisterController;
import com.fv.shiftreport.dao.StaticDataDaoImpl;
import com.fv.shiftreport.dao.UserDaoImpl;
import com.fv.shiftreport.manager.AccountManagerImpl;
import com.fv.shiftreport.manager.StaticDataManagerImpl;
import com.fv.shiftreport.model.Role;

public class RegisterView extends JDialog {
	
	RegisterController controller = new RegisterController();
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JPanel panel_2;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JPasswordField txtPassword;
	private JComboBox<Role> cmboRole;
	private JLabel lblUserName;
	private JTextField txtUser;
	private JButton btnClear;
	private JButton btnRegister;

	public RegisterView(MainView mainView) throws SQLException {
		init();
		controller.setRegisterView(this);
		controller.setStaticDataManager(new StaticDataManagerImpl());
		controller.getStaticDataManager().setStaticDataDao(new StaticDataDaoImpl());;
		controller.setAccountManager(new AccountManagerImpl());
		controller.getAccountManager().setUserDao(new UserDaoImpl());
		controller.init(mainView);
	}
	
	public void init(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterView.class.getResource("/images/fvlogo.jpg")));
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
		setTitle("Register");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 11, 348, 261);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 23, 90, 214);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(5, 1, 10, 10));
		
		label = new JLabel("First Name");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
		label_1 = new JLabel("Last Name");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblUserName);
		
		label_2 = new JLabel("Password");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		label_3 = new JLabel("Role");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);
		
		panel_2 = new JPanel();
		panel_2.setBounds(106, 23, 232, 214);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(5, 1, 10, 10));
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		panel_2.add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		panel_2.add(txtLastName);
		
		txtUser = new JTextField();
		panel_2.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		panel_2.add(txtPassword);
		
		cmboRole = new JComboBox<Role>();
		panel_2.add(cmboRole);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(201, 283, 85, 35);
		getContentPane().add(btnRegister);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(296, 283, 73, 35);
		getContentPane().add(btnClear);
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 350, 300 );
		this.setBounds ( ss.width / 2 - frameSize.width / 2, 
		                  ss.height / 2 - frameSize.height / 2,
		                  420, 373 );
	}
	
	public JTextField getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(JTextField txtUser) {
		this.txtUser = txtUser;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JComboBox<Role> getCmboRole() {
		return cmboRole;
	}

	public void setCmboRole(JComboBox<Role> cmboRole) {
		this.cmboRole = cmboRole;
	}

	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	public void setTxtFirstName(JTextField txtFirstName) {
		this.txtFirstName = txtFirstName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public void setTxtLastName(JTextField txtLastName) {
		this.txtLastName = txtLastName;
	}

	public JButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}

	
}

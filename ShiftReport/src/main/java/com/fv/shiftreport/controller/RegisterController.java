package com.fv.shiftreport.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.fv.shiftreport.manager.AccountManager;
import com.fv.shiftreport.manager.StaticDataManager;
import com.fv.shiftreport.model.Role;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.Util;
import com.fv.shiftreport.view.MainView;
import com.fv.shiftreport.view.RegisterView;

public class RegisterController {

	protected StaticDataManager staticDataManager;
	protected  AccountManager accountManager;
	protected RegisterView registerView;
	private MainView mainView;
	
	public void init(MainView mainView) throws SQLException{
		this.mainView = mainView;
		List<Role> roleList = staticDataManager.getAllRole();
		for(Role role: roleList){
			registerView.getCmboRole().addItem(role);
		}	
		registerView.getBtnRegister().addActionListener(new RegisterActionListener());
		registerView.getBtnClear().addActionListener(new ClearActionListener());
	}

	public StaticDataManager getStaticDataManager() {
		return staticDataManager;
	}

	public void setStaticDataManager(StaticDataManager staticDataManager) {
		this.staticDataManager = staticDataManager;
	}

	public RegisterView getRegisterView() {
		return registerView;
	}

	public void setRegisterView(RegisterView registerView) {
		this.registerView = registerView;
	}
	
	public class RegisterActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			UserRequest request= new UserRequest();
			request.setCreatedBy(mainView.getLoggedInUser().getId());
			request.setUpdatedBy(mainView.getLoggedInUser().getId());
			request.setFirstName(registerView.getTxtFirstName().getText());
			request.setLastName(registerView.getTxtLastName().getText());
			request.setUserName(registerView.getTxtUser().getText());
			request.setPassword(String.valueOf(registerView.getTxtPassword().getPassword()));
			request.setRole((Role)registerView.getCmboRole().getSelectedItem());
			request.setUpdatedBy(mainView.getLoggedInUser().getId());
			request.setUpdatedDate(DatabaseUtil.getCurrentTimeStamp());
			request.setCreatedDate(DatabaseUtil.getCurrentTimeStamp());
			try {
				accountManager.registerUser(request);
				JOptionPane.showMessageDialog(mainView,"Successfully registered "+request.getUserName() ,"Save", JOptionPane.PLAIN_MESSAGE);
				registerView.dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(mainView, e1.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
				Util.writeToFile("error", e1.getMessage());
			}
			
		}
		
	}
	public class ClearActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			registerView.getTxtFirstName().setText("");
			registerView.getTxtLastName().setText("");
			registerView.getTxtUser().setText("");
		    registerView.getTxtPassword().setText("");
			registerView.getCmboRole().setSelectedIndex(1);
				
		}
		
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
}

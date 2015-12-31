package com.fv.shiftreport.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.fv.shiftreport.dao.UserDaoImpl;
import com.fv.shiftreport.manager.LoginManager;
import com.fv.shiftreport.manager.LoginManagerImpl;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.SettingUtil;
import com.fv.shiftreport.view.LoginView;
import com.fv.shiftreport.view.MainView;

public class LoginController {

	protected LoginView loginView;
	protected LoginManager loginManager;
	

	public LoginController() {
	}

	public void init()  {
		try{
			SettingUtil.populateProps();
			loginManager = new LoginManagerImpl();
			loginManager.setUserDao(new UserDaoImpl());
			loginView.getBtnCancel().addActionListener(new CancelButtonListener());
			loginView.getBtnLogIn().addActionListener(new LogInButtonListener());	
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(loginView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public class CancelButtonListener implements ActionListener {
        
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public class LogInButtonListener implements ActionListener {
		LoginView view = loginView;
		public void actionPerformed(ActionEvent e) {
			try {
				String user = view.getTxtUser().getText();
				char[] pwd = view.getTxtPassword().getPassword();
				if(user != null && pwd != null && !user.isEmpty() && pwd.length > 0){
					UserResponse response;
					
					response = loginManager.getUserByCredential(new UserRequest(user,new String(pwd)));
					if(null!=response){
						loginView.dispose();
						openMainVeiw(response);
						return;
					}
	                
				}
				view.getLblError().setText("Invalid Username/Password");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void openMainVeiw(final UserResponse user){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainView(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

package com.fv.shiftreport.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

import com.fv.shiftreport.dao.UserDaoImpl;
import com.fv.shiftreport.manager.AccountManager;
import com.fv.shiftreport.manager.AccountManagerImpl;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.SettingUtil;
import com.fv.shiftreport.util.Util;
import com.fv.shiftreport.view.LoginView;
import com.fv.shiftreport.view.MainView;

public class LoginController {

	protected LoginView loginView;
	protected AccountManager loginManager;
	

	public LoginController() {
	}

	public void init()  {
		try{
			SettingUtil.populateProps();
			initForSql();
			loginManager = new AccountManagerImpl();
			loginManager.setUserDao(new UserDaoImpl());
			loginView.getBtnCancel().addActionListener(new CancelButtonListener());
			loginView.getBtnLogIn().addActionListener(new LogInButtonListener());
			loginView.getBtnLogIn().addKeyListener(new LoginKeyListener());
			loginView.getTxtUser().addKeyListener(new LoginKeyListener());
			loginView.getTxtPassword().addKeyListener(new LoginKeyListener());
		}catch(Exception e){
			Util.writeToFile("error", e.getMessage());
			JOptionPane.showMessageDialog(loginView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void initForSql(){
		try{
			File dir = new File(System.getProperty("user.dir"));
			File[] sqlFileList = dir.listFiles();
			for(File sqlFile : sqlFileList){
				if(sqlFile.isFile() && new String(Base64.decodeBase64(sqlFile.getName().getBytes())).contains("fv")
						&& new String(Base64.decodeBase64(sqlFile.getName().getBytes())).contains("sql")){
					String sql = Util.fileToString(sqlFile);
					if(null != sql && !sql.trim().isEmpty()){
						Util.writeToFile("Info", "Done executing "+sqlFile);
					}else{
						Util.writeToFile("Info", "No File Executed");
					}
					
				}
			}
		}catch(Exception e){
			Util.writeToFile("Error", e.getMessage());
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
	
	public class LoginKeyListener implements KeyListener{

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				loginView.getBtnLogIn().doClick();
			}
			
		}
		
	}
	

	public class LogInButtonListener implements ActionListener {
		LoginView view = loginView;
		public void actionPerformed(ActionEvent e) {
			login();
		}
		private void login() {
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

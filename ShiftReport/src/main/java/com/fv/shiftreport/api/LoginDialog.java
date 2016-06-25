package com.fv.shiftreport.api;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.fv.shiftreport.manager.AccountManager;
import com.fv.shiftreport.model.UserRequest;
import com.fv.shiftreport.model.UserResponse;
import com.fv.shiftreport.util.RoleConstant;
import com.fv.shiftreport.util.Util;

public class LoginDialog extends JDialog {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    
    protected AccountManager loginManager;
 
    public LoginDialog(Frame parent) {
        super(parent, "Admin Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	try{
            		UserResponse response = loginManager.getUserByCredential(new UserRequest(getUsername(),getPassword()));
                    if (response!= null ) {
                    	if(response.getRoleId()==RoleConstant.ADMIN_ROLE){
                    		succeeded = true;
                            dispose();	
                    	}else{
                    		JOptionPane.showMessageDialog(LoginDialog.this,
                                    "User not previlage to load transactions",
                                    "Login",
                                    JOptionPane.ERROR_MESSAGE);
                            tfUsername.setText("");
                            pfPassword.setText("");
                            succeeded = false;
                    	}
                    } else {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Invalid credentials.\n User must be",
                                "Login",
                                JOptionPane.ERROR_MESSAGE);
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;
     
                    }
            	}catch(Exception ex){
            		Util.writeToFile("error", ex.getMessage());
            		JOptionPane.showMessageDialog(LoginDialog.this, ex.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
    
    public void setAccountManager(AccountManager accManager){
    	this.loginManager = accManager;
    }
    
    public AccountManager getAccountManager(){
    	return loginManager;
    }
}

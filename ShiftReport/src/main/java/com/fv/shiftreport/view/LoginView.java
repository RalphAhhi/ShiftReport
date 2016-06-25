package com.fv.shiftreport.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.binary.Base64;

import com.fv.shiftreport.controller.LoginController;
import com.fv.shiftreport.util.DatabaseUtil;
import com.fv.shiftreport.util.Util;

public class LoginView extends JFrame {
	
	LoginController controller = new LoginController();
	private static final long serialVersionUID = 1L;
	
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnCancel;
	private JButton btnLogIn;
	private JLabel lblError;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		init();
		controller.setLoginView(this);
		controller.init();
	}
	
	public void init(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/fvlogo.jpg")));
		setResizable(false);
		
		setVisible(true);
		
		setTitle("Log-in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User Name: ");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(28, 129, 72, 24);
		getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(38, 159, 62, 24);
		getContentPane().add(lblPassword);
		
		txtUser = new JTextField();
		lblUser.setLabelFor(txtUser);
		txtUser.setBounds(110, 132, 208, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		lblPassword.setLabelFor(txtPassword);
		txtPassword.setBounds(110, 162, 208, 20);
		getContentPane().add(txtPassword);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(110, 193, 72, 23);
		getContentPane().add(btnLogIn);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(194, 193, 84, 23);
		getContentPane().add(btnCancel);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("/images/fvicon.jpg")));
		lblLogo.setBounds(138, 11, 140, 110);
		getContentPane().add(lblLogo);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setBounds(110, 227, 187, 14);
		getContentPane().add(lblError);
		
		Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = new Dimension ( 350, 300 );
		this.setBounds ( ss.width / 2 - frameSize.width / 2, 
		                  ss.height / 2 - frameSize.height / 2,
		                  frameSize.width, frameSize.height );
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

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public void setBtnLogIn(JButton btnLogIn) {
		this.btnLogIn = btnLogIn;
	}

	public JLabel getLblError() {
		return lblError;
	}

	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}
}

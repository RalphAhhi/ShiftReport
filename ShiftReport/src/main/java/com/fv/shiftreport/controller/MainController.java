package com.fv.shiftreport.controller;

import javax.swing.JOptionPane;

import com.fv.shiftreport.view.MainView;

public class MainController {

	protected MainView mainView;

	public MainController() {
	}

	public void init()  {
		try{
		//mainView.getTxtUser().setText(mainView.getLoggedInUser().getLastName()+" , "+mainView.getLoggedInUser().getFirstName());
				
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(mainView, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

}

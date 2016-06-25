package com.fv.shiftreport.controller;

import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fv.shiftreport.manager.ProductManager;
import com.fv.shiftreport.model.Products;
import com.fv.shiftreport.util.Util;
import com.fv.shiftreport.view.InventoryView;

public class InventoryController {

	protected InventoryView view;
	protected ProductManager productManager;
	

	public InventoryController() {
	}

	public void init()  {
		try{
			view.getPlist().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel<Products> model = new DefaultListModel<Products>();
			for(Products p : productManager.getAllLubeProduct()){
				model.addElement(p);
			}
			view.getPlist().setModel(model);
			view.getPlist().addListSelectionListener(new ProductPickListener());
		}catch(Exception e){
			Util.writeToFile("error", e.getMessage());
			JOptionPane.showMessageDialog(view, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public InventoryView getView() {
		return view;
	}

	public void setView(InventoryView view) {
		this.view = view;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	public class ProductPickListener implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			Products selected = view.getPlist().getSelectedValue();
			productManager.getInventoryTransaction(selected);
			productManager.getInventory(selected);
			
		}
		
	}
}


		
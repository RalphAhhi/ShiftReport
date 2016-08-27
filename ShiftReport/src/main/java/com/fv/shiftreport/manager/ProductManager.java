package com.fv.shiftreport.manager;

import java.util.List;

import com.fv.shiftreport.dao.ProductDao;
import com.fv.shiftreport.model.Inventory;
import com.fv.shiftreport.model.InventoryTransaction;
import com.fv.shiftreport.model.Products;

public interface ProductManager {

	public List<Products> getLubesProducts() throws Exception;
	public List<Products> getGasolineProducts() throws Exception;
	public Products getProductById(int productId) throws Exception ;
	
	public void setProductDao(ProductDao productDao);
	public List<Products> getAllProduct()throws Exception ;
	public void synchProducts(List<Products> productList)throws Exception ;
	public List<Products> getAllLubeProduct() throws Exception ;
	public Inventory getInventory(Products selected);
	public List<InventoryTransaction> getInventoryTransaction(Products selected);
}

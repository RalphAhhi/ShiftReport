package com.fv.shiftreport.manager;

import java.util.ArrayList;
import java.util.List;

import com.fv.shiftreport.dao.ProductDao;
import com.fv.shiftreport.model.Products;
import com.fv.shiftreport.util.ProductTypeConstant;


public class ProductManagerImpl implements ProductManager {
	
	private ProductDao productDao;

	public List<Products> getLubesProducts() throws Exception {
		return productDao.fetchProductsByType(ProductTypeConstant.LUBE_PRODUCT);
	}
	
	public Products getProductById(int productId) throws Exception {
		return productDao.fetchProductById(productId);
	}


	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	public List<Products> getGasolineProducts() throws Exception {
		return productDao.fetchProductsByType(ProductTypeConstant.GASOLINE_PRODUCT);
		
	}

	public List<Products> getAllProduct() throws Exception {
		List<Products> productList = new ArrayList<Products>();
		productList.addAll(productDao.fetchProductsByType(ProductTypeConstant.GASOLINE_PRODUCT));
		productList.addAll(productDao.fetchProductsByType(ProductTypeConstant.LUBE_PRODUCT));
		return productList;
	}
	
	public List<Products> getAllLubeProduct() throws Exception {
		List<Products> productList = new ArrayList<Products>();
		productList.addAll(productDao.fetchProductsByType(ProductTypeConstant.LUBE_PRODUCT));
		return productList;
	}

	public void synchProducts(List<Products> productList) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void getInventory(Products selected) {
		// TODO Auto-generated method stub
		
	}

	public void getInventoryTransaction(Products selected) {
		// TODO Auto-generated method stub
		
	}
	


}

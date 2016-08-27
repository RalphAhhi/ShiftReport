package com.fv.shiftreport.dao;

import java.sql.SQLException;
import java.util.List;

import com.fv.shiftreport.model.Inventory;
import com.fv.shiftreport.model.Products;

public interface ProductDao {

	public List<Products> fetchProductsByType(int productIdType)throws Exception;

	public Products fetchProductById(int productId) throws SQLException;

	public Inventory fetchInventory(Products product);
}

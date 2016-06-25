package com.fv.shiftreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fv.shiftreport.model.Products;
import com.fv.shiftreport.util.DatabaseUtil;

public class ProductDaoImpl implements ProductDao{

	public List<Products> fetchProductsByType(int productIdType) throws Exception {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,description,price,producttypeid from fv.products where producttypeid = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, productIdType);
			return buildList( ps.executeQuery());
		}finally {
			if(null!=dbConnection){
				dbConnection.close();
			}
			if(null!=ps){
				ps.close();
			}
			
		}

	}
	
	private Products build(ResultSet rs) throws SQLException{
		    Products response = new Products();
			response.setId(rs.getInt("id"));
			response.setDescription(rs.getString("description"));
			response.setPrice(rs.getDouble("price"));
			response.setProductType(rs.getInt("producttypeid"));
		return response;
	}
	
	private List<Products> buildList(ResultSet rs) throws SQLException{
		List<Products> productList = new ArrayList<Products>(); 
		while (rs.next()){
			productList.add(this.build(rs));
		}
		return productList;
	}

	public Products fetchProductById(int productId) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		try{
			dbConnection = DatabaseUtil.getDBConnection();
			String sql = "select id,description,price,producttypeid from fv.products where id = ?";
			ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return build(rs);
			}
			return null;
		}finally {
			if(null!=dbConnection){
				dbConnection.close();
			}
			if(null!=ps){
				ps.close();
			}
			
		}
	}

}

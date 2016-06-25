package com.fv.shiftreport.model;

import java.io.Serializable;
import java.util.List;

public class SynchData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2328081467938905109L;
	
	private List<UserResponse> user;
	private List<Transaction> trxList;
	private List<Products> productList;
	
	public SynchData(List<UserResponse> user,List<Transaction> trxList, List<Products> productList){
		this.user = user;
		this.trxList= trxList;
		this.productList = productList;
	}
    public SynchData(){
		
	}
	public List<UserResponse> getUser() {
		return user;
	}
	public void setUser(List<UserResponse> user) {
		this.user = user;
	}
	public List<Transaction> getTrxList() {
		return trxList;
	}
	public void setTrxList(List<Transaction> trxList) {
		this.trxList = trxList;
	}
	public List<Products> getProductList() {
		return productList;
	}
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}
	
}

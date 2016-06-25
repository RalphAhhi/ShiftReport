package com.fv.shiftreport.model;

import java.io.Serializable;

public class Collection extends Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3183581136947260650L;
	
	private int id;
	private String customer;
	private String bankCheck;
	private Double amount;
	private String universalTrxId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankCheck() {
		return bankCheck;
	}
	public void setBankCheck(String bankCheck) {
		this.bankCheck = bankCheck;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getUniversalTrxId() {
		return universalTrxId;
	}
	public void setUniversalTrxId(String universalTrxId) {
		this.universalTrxId = universalTrxId;
	}
}

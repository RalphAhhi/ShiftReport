package com.fv.shiftreport.model;

import java.io.Serializable;

public class AccountReceivable extends Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5785233854213183558L;
	
	private int id;
	private String customer;
	private String invoiceNo;
	private Double amount;
	private String universalTrxId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getUniversalTrxId() {
		return universalTrxId;
	}
	public void setUniversalTrxId(String universalTrxId) {
		this.universalTrxId = universalTrxId;
	}
	
}

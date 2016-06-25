package com.fv.shiftreport.model;

import java.io.Serializable;

public class SubTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3303551397153978906L;
	
	private int id;
	private int productId;
	private int pumpNumber;
	private Double opening;
	private Double closing;
	private Double liters;
	private Double calib;
	private Double sold;
	private Double price;
	private Double amount;
	private String universalTrxId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getOpening() {
		return opening;
	}
	public void setOpening(Double opening) {
		this.opening = opening;
	}
	public Double getClosing() {
		return closing;
	}
	public void setClosing(Double closing) {
		this.closing = closing;
	}
	public Double getLiters() {
		return liters;
	}
	public void setLiters(Double liters) {
		this.liters = liters;
	}
	public Double getCalib() {
		return calib;
	}
	public void setCalib(Double calib) {
		this.calib = calib;
	}
	public Double getSold() {
		return sold;
	}
	public void setSold(Double sold) {
		this.sold = sold;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getPumpNumber() {
		return pumpNumber;
	}
	public void setPumpNumber(int pumpNumber) {
		this.pumpNumber = pumpNumber;
	}
	public String getUniversalTrxId() {
		return universalTrxId;
	}
	public void setUniversalTrxId(String universalTrxId) {
		this.universalTrxId = universalTrxId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

}

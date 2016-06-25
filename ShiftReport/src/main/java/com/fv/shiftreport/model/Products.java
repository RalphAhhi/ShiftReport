package com.fv.shiftreport.model;

import java.io.Serializable;

public class Products implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804301598106454985L;
	private String id;
	private String description;
	private int productType;
	private double price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString(){
		return this.getDescription();
	}
}

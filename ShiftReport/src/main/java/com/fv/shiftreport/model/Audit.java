package com.fv.shiftreport.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public abstract class Audit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2306198288040519447L;
	
	protected Timestamp updatedDate;
	protected Timestamp createdDate;
	protected String createdBy;
	protected String updatedBy;
	
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}

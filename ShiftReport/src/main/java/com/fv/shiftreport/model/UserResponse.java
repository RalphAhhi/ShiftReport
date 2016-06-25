package com.fv.shiftreport.model;

import java.io.Serializable;

public class UserResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5308958934523662736L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private int roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}

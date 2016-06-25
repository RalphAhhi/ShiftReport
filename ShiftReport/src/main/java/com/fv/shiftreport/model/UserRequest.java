package com.fv.shiftreport.model;

public class UserRequest extends Audit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474441059988867968L;
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Role role;
	
	public UserRequest(){
		
	}
	
	public UserRequest(String userName,String pwd){
		this.userName = userName;
		this.password = pwd;
				
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

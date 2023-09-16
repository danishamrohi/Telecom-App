package com.cgi.customer.model;

import javax.persistence.*;

@Entity
public class Customer {
	@Id
	private String userName;
	private int phoneNumber;
	private String name;
	private String password;
	private String emailId;

	public Customer() {	}

	public Customer( int phoneNumber,String name,String userName,String password, String emailId) {
		this.name=name;
		this.emailId=emailId;
		this.password=password;
		this.phoneNumber=phoneNumber;
		this.userName=userName;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}

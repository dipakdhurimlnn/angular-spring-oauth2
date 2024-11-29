package com.spring.auth.server.demo.rest.model;

import com.spring.auth.server.demo.entity.Customer;

public class RestCustomer {
	private int id;

	private String name;

	private String email;

	private String mobileNumber;

	private String role;

	public RestCustomer(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.mobileNumber = customer.getMobileNumber();
		this.role = customer.getRole();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	 "id": 1,
//	    "name": "Happy",
//	    "email": "happy@example.com",
//	    "mobileNumber": "9876548337",
//	    "role": "admin",
}

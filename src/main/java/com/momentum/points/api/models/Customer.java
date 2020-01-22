package com.momentum.points.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Customer {

	@Id
	private String docId;
	
	private String customerId;	
	private String customerName;
	
	
	public Customer(String customerId, String customerName) {
		super();
		this.setCustomerId(customerId);
		this.customerName = customerName;
	
	}
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}

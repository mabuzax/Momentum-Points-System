package com.momentum.points.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PointsAccount {	
	@Id
	private String docId;
	private String accountId;
	private String customerId;
	private int pointsBalance;  //Available points for the customer	
	
	
	public PointsAccount(String accountId, String customerId, int pointsBalance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.pointsBalance = pointsBalance;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}	
	
	
	public int getPointsBalance() {
		return pointsBalance;
	}
	
	public void setPointsBalance(int pointsBalance) {
		this.pointsBalance = pointsBalance;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	
	
	
	
}

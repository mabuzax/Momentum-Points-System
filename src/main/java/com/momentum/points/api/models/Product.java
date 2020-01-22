package com.momentum.points.api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	@Id
	private ObjectId id;
	
	private String productCode;	
	private String productName;
	private int pointsCost;	
	
	public Product(String productCode, String productName, int pointsCost) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.pointsCost = pointsCost;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public int getPointsCost() {
		return pointsCost;
	}
	public void setPointsCost(int pointsCost) {
		this.pointsCost = pointsCost;
	}

	
	
}

package com.momentum.points.api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomResponse {

	private HttpStatus status;
	private String message;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

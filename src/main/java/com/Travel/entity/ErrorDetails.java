package com.Travel.entity;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorDetails {

	private Date timestamp;
	private List<String> message;
	private String details;

	public ErrorDetails(Date timestamp, List<String> message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails() {
		super();

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
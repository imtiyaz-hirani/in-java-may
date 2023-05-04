package com.restapi.dto;

public class TrasactionRequestDto {
	private Long senderId;
	private Long benificiaryId; 
	private double amount; 
	private String mandate;
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getBenificiaryId() {
		return benificiaryId;
	}
	public void setBenificiaryId(Long benificiaryId) {
		this.benificiaryId = benificiaryId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMandate() {
		return mandate;
	}
	public void setMandate(String mandate) {
		this.mandate = mandate;
	} 
	
	
}

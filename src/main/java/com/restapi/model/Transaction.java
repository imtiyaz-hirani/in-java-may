package com.restapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private Long senderId; 
	
	private Long benificiaryId; 
	
	private double amount; 
	
	private LocalDate dateOfTransaction; 
	
	private Long approverId;

	@OneToOne
	private Mandate mandate; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public Mandate getMandate() {
		return mandate;
	}

	public void setMandate(Mandate mandate) {
		this.mandate = mandate;
	} 
	
	
}

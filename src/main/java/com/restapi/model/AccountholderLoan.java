package com.restapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AccountholderLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private AccountHolder accountHolder; 
	
	@OneToOne
	private Loan loan; 
	
	private LocalDate dateOfLoanIssue; 
	
	private double rateOfInterest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public LocalDate getDateOfLoanIssue() {
		return dateOfLoanIssue;
	}

	public void setDateOfLoanIssue(LocalDate dateOfLoanIssue) {
		this.dateOfLoanIssue = dateOfLoanIssue;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	} 
	
	
	 
}

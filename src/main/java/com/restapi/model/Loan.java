package com.restapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.restapi.enums.LoanType;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	private String loanNo; 
	
	@Enumerated(EnumType.STRING)
	private LoanType loanType; 
	 
	private int tenure; //in months

	/*
	 * @ManyToMany(mappedBy = "loan") private List<AccountHolder> accountHolder;
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	
	
}

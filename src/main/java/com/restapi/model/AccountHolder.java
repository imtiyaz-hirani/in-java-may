package com.restapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;
@Entity
public class AccountHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToOne
	private Account account;  //type and number 
	
	@OneToOne
	private Company company;
	
	
	/*
	 * @ManyToMany
	 * @ManyToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "accountholder_loan", joinColumns
	 * = @JoinColumn(name="accountholder_id"), inverseJoinColumns
	 * = @JoinColumn(name="loan_id")) private List<Loan> loan;
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	} 
	
	
	
}

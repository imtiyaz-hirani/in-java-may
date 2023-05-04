package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.AccountholderLoan;
import com.restapi.repository.AccountholderLoanRepository;

@Service
public class AccountholderLoanService {

	@Autowired
	private AccountholderLoanRepository repo;

	public AccountholderLoan saveAHL(AccountholderLoan ahl) {
		 
		return repo.save(ahl);
	} 
	
	
}

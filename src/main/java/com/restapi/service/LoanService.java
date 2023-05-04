package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Loan;
import com.restapi.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	public Loan postLoan(Loan loan) {
		 
		return loanRepository.save(loan);
	}

	public Loan fetchLoanById(Long loanId) {
		 
		return loanRepository.findById(loanId).get();
	}
}

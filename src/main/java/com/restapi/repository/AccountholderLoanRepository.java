package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.AccountholderLoan;

public interface AccountholderLoanRepository extends JpaRepository<AccountholderLoan, Long>{

}

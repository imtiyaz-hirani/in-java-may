package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}

package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}

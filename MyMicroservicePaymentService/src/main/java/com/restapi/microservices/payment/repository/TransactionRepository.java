package com.restapi.microservices.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.microservices.payment.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

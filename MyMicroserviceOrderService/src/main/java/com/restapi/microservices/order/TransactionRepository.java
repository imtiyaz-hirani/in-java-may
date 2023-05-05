package com.restapi.microservices.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.microservices.order.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}

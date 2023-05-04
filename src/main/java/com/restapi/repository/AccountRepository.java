package com.restapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restapi.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query() //update insert
	 */}

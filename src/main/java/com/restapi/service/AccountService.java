package com.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo; 
	public Account insertAccount(Account account) {
		// TODO Auto-generated method stub
		/*
		 * List<Account> list = accountRepo.findAll(); list.stream().forEach(a->
		 * a.setBalance(10000)); accountRepo.saveAll(list);
		 */
		return accountRepo.save(account);
	}
	
	public void insertBatch(List<Account> list) {
		accountRepo.saveAll(list);
		
	}

}

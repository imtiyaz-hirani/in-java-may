package com.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.enums.LoanType;
import com.restapi.enums.MandateType;
import com.restapi.model.Account;
import com.restapi.model.AccountHolder;
import com.restapi.repository.AccountHolderRepository;

@Service
public class AccountHolderService {

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	public AccountHolder insertAccountHolder(AccountHolder accountHolder) {
		return accountHolderRepository.save(accountHolder);
	}

	public AccountHolder fetchAccountHolder(Long senderId) {
		 Optional<AccountHolder> optional = accountHolderRepository.findById(senderId);
		 if(!optional.isPresent()) {
			 return null; //Ideally throw ResourceNotFoundException
		 }
		return optional.get();
	}

	public List<AccountHolder> getAccountHolderByMandate(MandateType type) {
		return accountHolderRepository.getAccountHolderByMandate(type.toString());
	}

	public List<AccountHolder> getAccountHolderByMandateJPQL(MandateType type) {
		return accountHolderRepository.getAccountHolderByMandateJPQL(type);
				 
		}

	public List<Account> getAccountByLoanType(String loanType) {
		 
		return accountHolderRepository.getAccountByLoanType(LoanType.valueOf(loanType));
	}

}

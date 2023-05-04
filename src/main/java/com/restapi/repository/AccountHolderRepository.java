package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restapi.enums.LoanType;
import com.restapi.enums.MandateType;
import com.restapi.model.Account;
import com.restapi.model.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long>{

	@Query(value = "select * from account_holder where id "
			+ "IN (select sender_id from transaction where mandate_id "
			+ "IN (select id from mandate where mandate_type =?1))", nativeQuery = true)
	List<AccountHolder> getAccountHolderByMandate(String type);

	@Query("select ah from AccountHolder ah where ah.id "
			+ " IN (select t.senderId from Transaction t where t.mandate.mandateType=?1)")
	List<AccountHolder> getAccountHolderByMandateJPQL(MandateType type);

	@Query("select a from Account a where a.id "
			+ " IN (select ahl.accountHolder.account.id from AccountholderLoan ahl "
			+ " where ahl.loan.loanType=?1)")
	List<Account> getAccountByLoanType(LoanType loanType);
	 //
}
//JPQL : HQL: 

//RestAssured : API 
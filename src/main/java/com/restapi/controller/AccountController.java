package com.restapi.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.dto.AccountHolderRequestDto;
import com.restapi.dto.TrasactionRequestDto;
import com.restapi.enums.AccountType;
import com.restapi.enums.MandateType;
import com.restapi.model.Account;
import com.restapi.model.AccountHolder;
import com.restapi.model.AccountholderLoan;
import com.restapi.model.Company;
import com.restapi.model.Loan;
import com.restapi.model.Mandate;
import com.restapi.model.Transaction;
import com.restapi.service.AccountHolderService;
import com.restapi.service.AccountService;
import com.restapi.service.AccountholderLoanService;
import com.restapi.service.CompanyService;
import com.restapi.service.LoanService;
import com.restapi.service.MandateService;
import com.restapi.service.TransactionService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private CompanyService companyService; 
	@Autowired
	private AccountService accountService; 
	@Autowired
	private AccountHolderService accountHolderService;
	@Autowired
	private MandateService mandateService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private AccountholderLoanService accountholderLoanService;
	/* 
	  accountHolder = 
	  {
	  	"name" : "",
	  	account :{
	  		accountNumber : "",
	  		accountType : ""
	  	},
	  	company : {
	  		name : ""
	  	}
	  }
	  
	  {
	  	"accountHolderName" : "",
	  	 accountNumber : "",
	  	 accountType : "",
	  	 
	  }
	 */
	@PostMapping("/add/{cid}") //conventional
	public ResponseEntity<?> createAccountForAccountHolder(@RequestBody AccountHolderRequestDto dto,
			@PathVariable("cid") Long cid, AccountHolder accountHolder, Account account) { //DI
		/*  Check if company exists in DB  */
		Company company = companyService.fetchCompany(cid);
		if(company == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company ID Invalid");
		}
		/* Attach company and name to AccountHolder object */
		accountHolder.setCompany(company);
		accountHolder.setName(dto.getAccountHolderName());
		
		/* Prepare account object */
		account.setAccountNumber(dto.getAccountNumber());
		account.setAccountType(AccountType.valueOf(dto.getAccountType()));
		
		/* Save account in DB so that we get ID of the account */
		account = accountService.insertAccount(account);
		
		/* Attach account to AccountHolder : always attach with ID */
		accountHolder.setAccount(account);
		
		/* Save accountHolder in DB  */
		accountHolder = accountHolderService.insertAccountHolder(accountHolder); 
		return ResponseEntity.status(HttpStatus.OK).body(accountHolder); 
		
		/* Response DTO is needed for flat json responses. Nested Response is not advisable. */
		
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<?> doTransaction(@RequestBody TrasactionRequestDto dto, 
			Transaction transaction, Mandate mandate) { //DI
		
		 
		/* Validate Sender Id */
		AccountHolder sender = accountHolderService.fetchAccountHolder(dto.getSenderId());
		if(sender == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sender ID Invalid");
		
		/* Validate beneficiary Id */
		AccountHolder beneficiary =  accountHolderService.fetchAccountHolder(dto.getBenificiaryId());
		if(beneficiary == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Beneficiary ID Invalid");
		
		/* sender.id and beneficiary.id are same, if so- same account transfer not allowed */
		if(dto.getSenderId() == dto.getBenificiaryId())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Same account transfer not allowed");
		
		MandateType type = MandateType.valueOf(dto.getMandate());
		
		/* validate amount: should be >0 and <amount limit : limit comes from mandate */
		if(dto.getAmount() < 0 && dto.getAmount() > type.getLimit())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount not allowed for mandate: " + type);
		
		/* Amount should be less than or equal sender account balance */
		Account senderAccount = sender.getAccount();
		if(senderAccount.getBalance() < dto.getAmount())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficients Funds: Rs. " + senderAccount.getBalance());
		
		/* Prepare Transaction object */
		transaction.setSenderId(dto.getSenderId());
		transaction.setBenificiaryId(dto.getBenificiaryId());
		transaction.setAmount(dto.getAmount());
		transaction.setDateOfTransaction(LocalDate.now());
		 
		/* Prepare the Mandate and attach it to transaction */
		mandate.setCountry("INDIA"); //read this from UI
		mandate.setMandateType(type);
		mandate = mandateService.insertMandate(mandate);
		
		transaction.setMandate(mandate);
		
		/* Update balance of Sender and beneficiary accounts */
		 /* Fetch Account details for Sender and update the balance and save account */
		/* Fetch Account details for beneficiary and update the balance and save account */
		
		 Account beneficiaryAccount = beneficiary.getAccount();
		 
		 senderAccount.setBalance(senderAccount.getBalance() - dto.getAmount());
		 beneficiaryAccount.setBalance(beneficiaryAccount.getBalance() + dto.getAmount());
		
		  
		// accountService.insertAccount(senderAccount); //-- done
		// accountService.insertAccount(beneficiaryAccount); // --failed 
		 
		 List<Account> list = Arrays.asList(senderAccount,beneficiaryAccount);
		 accountService.insertBatch(list);
		 
		 
		 
		 /* Save the transaction */
		 transactionService.insertTransaction(transaction);
			return ResponseEntity.status(HttpStatus.OK).body("Transfer Success: Pending Approval");

	}
	
	@GetMapping("/accountholder/mandate/{mandate}")
	public ResponseEntity<?> getAccountHolderByMandate(@PathVariable("mandate") String mandate) {
		
		//convert mandate String into Enum 
		MandateType type = MandateType.valueOf(mandate);
		
		List<AccountHolder>  list = accountHolderService.getAccountHolderByMandate(type);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/accountholder/mandate/jpql/{mandate}")
	public ResponseEntity<?> getAccountHolderByMandateJPQL(@PathVariable("mandate") String mandate) {
		
		//convert mandate String into Enum 
		MandateType type = MandateType.valueOf(mandate);
		
		List<AccountHolder>  list = accountHolderService.getAccountHolderByMandateJPQL(type);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/loan/{loanType}")
	public List<Account> getAccountByLoanType(@PathVariable("loanType") String loanType) {
		/* write JPQL for this  */
		 return accountHolderService.getAccountByLoanType(loanType);
	}
	
	@PostMapping("/loan/ah/{ahid}/{loanId}")
	public AccountholderLoan issueLoanForAccountHolder(@PathVariable("ahid") Long ahid,
										  @PathVariable("loanId") Long loanId,
										  AccountholderLoan ahl) {
		/* Fetch AccountHolder by ahid */
		AccountHolder ah = accountHolderService.fetchAccountHolder(ahid); 
		/* Fetch Loan by loanId */
		Loan loan = loanService.fetchLoanById(loanId);
		
		/* Attach ah and loan to ahl */
		ahl.setLoan(loan);
		ahl.setAccountHolder(ah);
		
		ahl.setDateOfLoanIssue(LocalDate.now());
		ahl.setRateOfInterest(loan.getLoanType().getInterestRate());
		
		/* Save it in DB */
		return accountholderLoanService.saveAHL(ahl);
	}
	
	@PostMapping("/loan/add")
	public Loan postLoan(@RequestBody Loan loan) {
		return loanService.postLoan(loan);
	}
}

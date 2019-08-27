package org.lexusmanson.lexbudget.rest;

import java.security.Principal;
import java.util.List;

import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.lexusmanson.lexbudget.rest.error.TransactionNotFoundException;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.lexusmanson.lexbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts/")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountsService accountsService;
	
//	@GetMapping("/helloWorld")
//	public String hellowWorld() {
//		return "Hello World";
//	}
	
	@GetMapping("/{accountId}/transactions")
	public List<Transactions> getTransactions(@PathVariable int accountId, Principal principal){
		String user = principal.getName();
		return transactionService.getTransactions(accountId, user);
	}
	
	@GetMapping("/{accountId}/transactions/{transactionId}")
	public Transactions getTransaction(@PathVariable int accountId, @PathVariable int transactionId, Principal principal) {
		
		String user = principal.getName();
		Transactions theTransaction = transactionService.getTransaction(transactionId, user);
		
		if(theTransaction == null) {
			throw new TransactionNotFoundException("Transaction id not found - " + transactionId);
		}
		
		return theTransaction;
		
	}
	
	@PostMapping("/{accountId}/transactions")
	public Transactions addTransaction(@RequestBody Transactions theTransaction, @PathVariable int accountId, Principal principal) {
		String user = principal.getName();
		Accounts account = accountsService.getAccount(accountId, user);
		theTransaction.setAccountsId(account);
		transactionService.saveTransaction(theTransaction, user);
		
		return theTransaction;
	}
	
	@PutMapping("/{accountId}/transactions")
	public Transactions updateTransactions(@RequestBody Transactions transaction, @PathVariable int accountId, Principal principal) {
		String user = principal.getName();
		Accounts account = accountsService.getAccount(accountId, user);
		transaction.setAccountsId(account);
		transactionService.saveTransaction(transaction, user);
		
		return transaction;
	}
	
	@DeleteMapping("/{accountId}/transactions/{transactionId}")
	public String deleteTransactions(@PathVariable int accountId, @PathVariable int transactionId, Principal principal) {
		String user = principal.getName();
		//transactionService.getTransaction(transactionId, user);
		
		transactionService.deleteTransaction(accountId, transactionId, user);
		
		return "Deleted transaction id - " + transactionId;
		
	}
}

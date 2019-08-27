package org.lexusmanson.lexbudget.rest;

import java.security.Principal;
import java.util.List;

import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.rest.error.AccountNotFoundException;
import org.lexusmanson.lexbudget.service.AccountsService;
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
@RequestMapping("/api")
public class AccountRestController {
	
	@Autowired
	private AccountsService accountsService;
	
//	@GetMapping("/helloWorld")
//	public String helloWorld() {
//		return "Hello World";
//	}
	
	@GetMapping("/accounts")
	public List<Accounts> getAccounts(Principal principal){
		
		String user = principal.getName();
		
		return accountsService.getAccounts(user);
		
	}
	
	@GetMapping("/accounts/{accountId}")
	public Accounts getAccount(@PathVariable int accountId, Principal principal) {
		
		String user = principal.getName();
		Accounts theAccount = accountsService.getAccount(accountId, user);
		
		if(theAccount == null) {
			throw new AccountNotFoundException("Account id not found - " + accountId);
		}
		
		return theAccount;
	}
	
	@PostMapping("/accounts")
	public Accounts addAccount(@RequestBody Accounts theAccount, Principal principal) {
		
		theAccount.setId(0);
		String user = principal.getName();
		accountsService.saveAccount(theAccount, user);
		return theAccount;
	}
	
	@PutMapping("/accounts")
	public Accounts updateAccount(@RequestBody Accounts theAccount, Principal principal) {
		
		String user = principal.getName();
		accountsService.saveAccount(theAccount, user);

		return theAccount;
		
	}

	@DeleteMapping("/accounts/{accountId}")
	public String deleteCustomer(@PathVariable int accountId, Principal principal) {
		
		String user = principal.getName();
		Accounts theAccount = accountsService.getAccount(accountId, user);
		
		if(theAccount == null) {
			throw new AccountNotFoundException("Account id not found - " + accountId);
		}
		
		accountsService.DeleteAccount(accountId, user);
		
		return "Deleted customer id - " + accountId;
	}
	
}

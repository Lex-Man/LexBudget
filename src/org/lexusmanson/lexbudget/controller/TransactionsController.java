package org.lexusmanson.lexbudget.controller;

import java.security.Principal;
/**
 * The web-controller used to control with request about transactions.
 */
import java.util.List;
// 03000593156

import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.lexusmanson.lexbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Lex-Man
 *
 */
@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	/**
	 * Used to deal with requests that effect the account stored in the database.
	 */
	@Autowired
	private AccountsService accountsService;
	
	/**
	 * Used to deal with requests that effect the transactions stored in the database.
	 */
	@Autowired
	private TransactionService transactionService;
	
	
	@Autowired
	@Qualifier("transactionValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
	/**
	 * Displays a list of transactions associated with a particular account.
	 * @param theId - The id of the account to have it's transactions displayed.
	 * @param theModel - contains model attributes used by the web page.
	 * @return - the name of the file that displays all of the transactions for the supplied account.
	 */
	@GetMapping("/showAccount")
	public String showAccount(@RequestParam("accountId") int theId, Model theModel, Principal principal) {
		
		String user = principal.getName();
		Accounts tempAccount = accountsService.getAccount(theId, user);
		List<Transactions> transactions = transactionService.getTransactions(theId, user);
		System.out.println(transactions.toString());
		theModel.addAttribute("transaction", transactions);
		theModel.addAttribute("account", tempAccount);
		return "transactions/main";
	}
	
	/**
	 * Displays a form to allow users to add a new transaction that will be associated with the current
	 * account.
	 * @param theId - The id of the account to associate the new transaction with.
	 * @param theModel - contains model attributes used by the web page.
	 * @return - The name of the file that contains the form to add a new transaction.
	 */
	@GetMapping("/showFormForAdd")
	public String addTransaction(@RequestParam("accountId") int theId, Model theModel, Principal principal) {
		
		String user = principal.getName();
		Accounts account = accountsService.getAccount(theId, user);
		Transactions transaction = new Transactions();
		transaction.setAccountsId(account);
		theModel.addAttribute("transaction", transaction);
		theModel.addAttribute("accId", theId);
		return "transactions/transactionsForm";
	}
	
	/**
	 * Saves the new or updated transactions in the database. 
	 * @param transaction - the transaction to be saved.
	 * @return - a HTTP redirect request that returns the user to the page displayig all the transactions.
	 */
	@RequestMapping(value = "/saveTransaction", method = RequestMethod.POST)
	public String saveTransaction(@ModelAttribute("transaction") Transactions transaction, BindingResult bindingResult, Principal principal) {
		String name = principal.getName();
		validator.validate(transaction, bindingResult);
		if(bindingResult.hasErrors()) {
			return "transactions/transactionsForm";
		}
		transactionService.saveTransaction(transaction, name);
		int account = transaction.getAccountsId().getId();
		return "redirect:/transactions/showFormForAdd?accountId=" + account;
	}
	
	/**
	 * Deletes the transaction from the database.
	 * @param accId - the id of the account that the transaction is associated to.
	 * @param transId - the id of the transaction that will be deleted.
	 * @return - a HTTP redirect request that displays all of the accounts transactions.
	 */
	@GetMapping("/delete")
	public String deleteTransaction(@RequestParam("accountId") int accId, @RequestParam("transactionId") int transId, Principal principal) {
		String name = principal.getName();
		transactionService.deleteTransaction(transId, accId, name);
		return "redirect:/transactions/showAccount?accountId=" + accId;
	}
	
	/**
	 * Sets up a page to display a form used to update a transaction that is currently stored in the database.
	 * @param transId - The id of the transaction to be updated.
	 * @param accId - the id of the account that the transaction is related to.
	 * @param theModel - contains model attributes used by the web page.
	 * @return - the name of the file that contains the form used to update the transaction.
	 */
	@GetMapping("/showFormForUpdate")
	public String updateForm(@RequestParam("transactionId") int transId, @RequestParam("accountId") int accId, Model theModel, Principal principal) {
		String user = principal.getName();
		Transactions trans = transactionService.getTransaction(transId, user);
		theModel.addAttribute("transaction", trans);
		theModel.addAttribute("accId", accId);
		return "transactions/transactionsForm";
	}
}

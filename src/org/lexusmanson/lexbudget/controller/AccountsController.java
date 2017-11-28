package org.lexusmanson.lexbudget.controller;
/**
 * The Accounts Controller is used to do prepare web pages concerning the account model and pass incoming requests to the 
 * accounts service which is the used to deal with the accounts.
 * 
 */

import java.util.List;

import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping("/accounts")
public class AccountsController {
	
	/**
	 * Accounts service is used to complete all of the business logic for the application.
	 */
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	@Qualifier("accountsValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	/**
	 * Returns a web page displaying the accounts currently stored in the database.
	 * @param theModel - contains model attributes used by the web page.
	 * @return String containing the name of the server page to be served to the user.
	 */
	@GetMapping("/main")
	public String main(Model theModel) {
		List<Accounts> theAccounts = accountsService.getAccounts();
		theModel.addAttribute("accounts", theAccounts);
		
		return "accounts/main";
	}
	
	/**
	 * Displays a form used to add a new account to the database.
	 * @param theModel - contains model attribute used by the web page.
	 * @return String containing the nmae of the server page to be served to the user.
	 */
	@GetMapping("/showFormForAdd")
	public String addAccount(Model theModel) {
		
		//create model attribute to bind form data
		Accounts account = new Accounts();
		
		theModel.addAttribute("account", account);
		
		return "accounts/accountForm";
	}
	
	/**
	 * Takes a request to save a new account and passes the details to the accountsService.
	 * @param theAccount - the new account to be added to the database
	 * @return a HTTP redirect request that sends the user back to the main accounts page. 
	 */
	@RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
	public String saveAccount(@ModelAttribute("account") @Validated Accounts theAccount, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "accounts/accountForm";
		}
		accountsService.saveAccount(theAccount);
		return "redirect:/accounts/main";
	}

	/**
	 * Removes the account and any transactions associated with it from the database.
	 * @param theID - the reference of the account to be deleted.
	 * @return - a HTTP redirect request to the accounts page.
	 */
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam("accountId") int theID) {
		
		accountsService.DeleteAccount(theID);
		return "redirect:/accounts/main";
	}
	
	/**
	 * Displays a form to the user that is used to an account currently in the database.
	 * @param theID - The id of the account that will be updated.
	 * @param theModel - contains model attributes used by the website.
	 * @return - The file name of the JSP to be used to display the form.
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("accountId") int theID, Model theModel) {
		
		Accounts temp = accountsService.getAccount(theID);
		theModel.addAttribute("account", temp);
		return "accounts/accountForm";
	}
	
}

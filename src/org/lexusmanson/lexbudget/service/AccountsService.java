package org.lexusmanson.lexbudget.service;

import java.util.List;

import org.lexusmanson.lexbudget.entity.Accounts;

/**
 * This class deals with all of the business logic related to the 
 * accounts database.
 * 
 * @author Lex-Man
 *
 */

public interface AccountsService {

	/**
	 * Deals with any business logic needed before a new account is 
	 * placed into the database.
	 * @param account
	 */
	public void saveAccount(Accounts account, String name);
	
	/**
	 * Deals with any business logic needed when returning lists of accounts.
	 * @return
	 */
	public List<Accounts> getAccounts(String user);
	
	/**
	 * Deletes - Deals with any business logic needed before deleting an account.
	 * @param theId
	 */
	public void DeleteAccount(int theId, String name);
	
	/**
	 * Deals with any business needed before an account is returned by the user.
	 * @param Id
	 * @return
	 */
	public Accounts getAccount(int Id, String name);

}

package org.lexusmanson.lexbudget.dao;

/**
 * Contains the methods used to access the account DAO that are used to commit account 
 * information to the accounts database. 
 */

import java.util.List;

import org.lexusmanson.lexbudget.entity.Accounts;

public interface AccountsDAO {

	/**
	 * Saves the account object passed as account into the database.
	 * @param account - the account object to be stored in the database.
	 */
	public void saveAccount(Accounts account, String name);
	
	/**
	 * Returns a list of all of the account objects stored in the database.
	 * @return - the list of all the account objects store in the database.
	 */
	public List<Accounts> getAccounts(String user);

	/**
	 * Deletes the account with the id specified by accountId.
	 * @param accountId - the id of the account to be deleted.
	 */
	public void deleteAccount(int accountId, String user);
	
	/**
	 * Retrieves the account with the id accountId from the database and return it.
	 * @param accountId - the id of the account to with draw.
	 * @return - the account requested in the argument.
	 */
	public Accounts getAccount(int accountId, String name);

}

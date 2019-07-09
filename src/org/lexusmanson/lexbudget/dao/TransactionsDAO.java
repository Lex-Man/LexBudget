package org.lexusmanson.lexbudget.dao;

import java.util.List;

import org.lexusmanson.lexbudget.entity.Transactions;

/**
 * Interface used to manipulate and retrieve data from the transactions database.
 * @author Lex-Man
 *
 */

public interface TransactionsDAO {

	/**
	 * Saves the Transactions instance transaction into the database. 
	 * @param transaction
	 */
	public void saveTransaction(Transactions transaction);

	/**
	 * Returns a list of transactions associated with the account id.
	 * @param account
	 * @return
	 */
	public List<Transactions> getTransactions(int account);
	
	/**
	 * Deletes the transaction int transactionId from the database.
	 * @param transactionId
	 */
	public void deleteTransaction(int transactionId);

	/**
	 * Returns a Transactions instances with the transaction id transId.
	 * @param transId
	 * @return
	 */
	public Transactions getTransaction(int transId);
	
	
	
}

package org.lexusmanson.lexbudget.service;

import java.util.List;

import org.lexusmanson.lexbudget.entity.Transactions;

/**
 * This interface defines all the methods required to allow business 
 * logic to take place before committing the transactions to the database.
 *  
 * @author Lex-Man
 *
 */

public interface TransactionService {
	
	/**
	 * Allows business logic to take place before saving a transaction in the transactions 
	 * database.
	 * @param transaction
	 */
	public void saveTransaction(Transactions transaction);
	
	/**
	 * Allows any business logic to take place before reading information from the transactions
	 *  
	 * @param accountId
	 * @return
	 */
	public List<Transactions> getTransactions(int accountId);
	
	/**
	 * Deals with any business logic required before deleting a transaction.
	 * 
	 * @param transactionId
	 */
	public void deleteTransaction(int transactionId, int accountId);

	/**
	 * Deals with any business logic required before returning a transaction 
	 */
	public Transactions getTransaction(int transId);
	
	
}

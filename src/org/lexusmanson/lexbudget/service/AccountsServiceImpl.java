package org.lexusmanson.lexbudget.service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.lexusmanson.lexbudget.dao.AccountsDAO;
import org.lexusmanson.lexbudget.dao.TransactionsDAO;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This file implements both the AccountsService and TransactionService.
 * It deals with all of the business logic required before making CRUD operations 
 * to the database. 
 * @author Lex-Man
 *
 */

@Service
public class AccountsServiceImpl implements AccountsService, TransactionService{

	/**
	 * Allows access to the accountDAO object that deals with all CRUD operation
	 * on the accounts table. 
	 */	
	@Autowired
	private AccountsDAO accountDAO;
	
	/**
	 * Allows access to the transaction DAO object that deals with all CRUD operations 
	 * to the transactions table.
	 */
	@Autowired
	private TransactionsDAO transactionDAO;
	
	/**
	 * Passes the account to the accountDAO saveAccount method to 
	 * allow it to be saved in the accounts database.
	 */
	@Override
	@Transactional
	public void saveAccount(Accounts account) {

		accountDAO.saveAccount(account);
		
	}

	/**
	 * Calls the accountDAO's getAccount method and returns 
	 * the results.
	 */
	@Override
	@Transactional
	public List<Accounts> getAccounts() {
		
		return accountDAO.getAccounts();
	}

	/**
	 * Passes the id of the account to the accountDAO's DeleteAccount method. 
	 */
	@Override
	@Transactional
	public void DeleteAccount(int theId) {
		accountDAO.deleteAccount(theId);
	}

	/**
	 * Returns the account id with the primary key equal to the integer argument id.
	 * This is done by calling the accountDAO's getAccount method.
	 */
	@Override
	@Transactional
	public Accounts getAccount(int id) {
		
		return accountDAO.getAccount(id);
	}

	/**
	 * Updates the balance value of the transaction argument and passed in to the accountDAO's saveTransaction method.
	 */
	@Override
	@Transactional
	public void saveTransaction(Transactions transaction) {
//		double tempBalance = transaction.accountsId.getCurrentBalance() + transaction.getAmount();
//		transaction.accountsId.setCurrentBalance(tempBalance);
//		transaction.setBalance(tempBalance);
		transactionDAO.saveTransaction(transaction);
	
		
		this.updateTransactions(transaction.getAccountsId().getId());
	}

	/**
	 * Calls the transacionDAO's getTransactions method with the argument accountId and returns the resulting list of transactions.
	 */
	@Override
	@Transactional
	public List<Transactions> getTransactions(int accountId) {
		
		return transactionDAO.getTransactions(accountId);
	}

	/**
	 * Passes the transactionId to the transactionDAO's delete transaction method.
	 */
	@Override
	@Transactional
	public void deleteTransaction(int transactionId, int accountId) {
		
		transactionDAO.deleteTransaction(transactionId);
		this.updateTransactions(accountId);
	}

	/**
	 * Calls the transactionDAO's getTransaction method with the argument transId and returns the 
	 * resulting transaction.
	 */
	@Override
	@Transactional
	public Transactions getTransaction(int transId) {
		
		return transactionDAO.getTransaction(transId);
	}
	
	/**
	 * Helper method used to keep transactions consistent.
	 * @param accountId
	 */
	private void updateTransactions(int accountId) {
		Accounts theAccount = accountDAO.getAccount(accountId);
		List<Transactions> accTransaction = this.getTransactions(accountId);
		Stream<Transactions> transStream = accTransaction.stream();
		
		DoubleAdder da = new DoubleAdder();
		
		transStream.forEach(trans -> {
			da.add(trans.getAmount());
			trans.setBalance(da.sum());
		});
		
		theAccount.setTransactions(accTransaction);
		theAccount.setCurrentBalance(da.sum());
		this.saveAccount(theAccount);
	}

}

package org.lexusmanson.lexbudget.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This class is an implementation of the Transaction DAO.
 * It handles all transactions with the database.
 * 
 * @author Lex-Man
 *
 */

@Repository 
public class TransactionsDAOImpl implements TransactionsDAO{

	/**
	 * Gives access to the database transaction objects.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	AccountsDAO accountsDAO;
	
	/**
	 * Save a transaction in the database.
	 */
	@Override
	public void saveTransaction(Transactions transaction, String user) {
		
		Accounts account = accountsDAO.getAccount(transaction.getAccountsId().getId(), user);
		if (account == null){
			// TODO: add exception handerling
		}
		else if(account.getUsername().equals(user)) {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(transaction);
		} else {
			// TODO: add exception handerling
		}
		
	}

	/**
	 * Returns a list of all of the transaction related to the account represented by 
	 * account which is the primary key for the account account table.
	 */
	@Override
	public List<Transactions> getTransactions(int account, String user) {
		
		Accounts tempAccount = accountsDAO.getAccount(account, user);
		
		if(tempAccount == null) {
			// TODO throw exception
		} else if (tempAccount.getUsername().equals(user) ) {
			Session currentSession = sessionFactory.getCurrentSession();
			
			Query<Transactions> theQuery =  currentSession.createQuery("from Transactions where accountsId.id=:var order by date", Transactions.class);

			theQuery.setParameter("var", account);
			List<Transactions> trans = theQuery.getResultList();
			
			return trans;
		} else {
			//TODO throw exception
		}
		
		return null;

	}

	/**
	 * Deletes the transaction action specified by transactionId. transactionId represents
	 * the primary key of the transaction database. 
	 */
	@Override
	public void deleteTransaction(int transactionId, String user) {
		
		Transactions temp = this.getTransaction(transactionId, user);
		Accounts tempAccount = temp.getAccountsId();
		
		if(tempAccount == null) {
			// TODO: throw exception
		} else if (tempAccount.getUsername().equals(user)) {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Transactions> theQuery = currentSession.createQuery("delete from Transactions where id=:var");
			theQuery.setParameter("var", transactionId);
			theQuery.executeUpdate();
		} else {
			// TODO: throw exception
		}
		
	}

	/**
	 * Returns a single transaction represented by the transID, which represents the primary
	 * key inside the transaction table.
	 */
	@Override
	public Transactions getTransaction(int transId, String user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Transactions tempTransaction = currentSession.get(Transactions.class, transId);
		
		if(tempTransaction == null) {
			// TODO throw exception
		} else if (tempTransaction.getAccountsId().getUsername().equals(user)) {
			return tempTransaction;
		} else {
			// TODO throw exception
		}
		
		return null;
	}

}

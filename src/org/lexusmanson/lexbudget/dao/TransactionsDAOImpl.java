package org.lexusmanson.lexbudget.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	
	/**
	 * Save a transaction in the database.
	 */
	@Override
	public void saveTransaction(Transactions transaction) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(transaction);
		
	}

	/**
	 * Returns a list of all of the transaction related to the account represented by 
	 * account which is the primary key for the account account table.
	 */
	@Override
	public List<Transactions> getTransactions(int account) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =  currentSession.createQuery("from Transactions where accountsId.id=:var order by date", Transactions.class);

		theQuery.setParameter("var", account);
		List<Transactions> trans = theQuery.getResultList();
		
		return trans;
	}

	/**
	 * Deletes the transaction action specified by transactionId. transactionId represents
	 * the primary key of the transaction database. 
	 */
	@Override
	public void deleteTransaction(int transactionId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Transactions where id=:var");
		theQuery.setParameter("var", transactionId);
		theQuery.executeUpdate();
	}

	/**
	 * Returns a single transaction represented by the transID, which represents the primary
	 * key inside the transaction table.
	 */
	@Override
	public Transactions getTransaction(int transId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		return currentSession.get(Transactions.class, transId);
	}

}

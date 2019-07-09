package org.lexusmanson.lexbudget.dao;

/**
 * Implements the AccountsDAO interface file. 
 * It allows access to the Accounts table.  So data can be deleted, or inserted.
 */

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Lex-Man
 *
 */

@Repository 
public class AccountsDAOImpl implements AccountsDAO{

	/**
	 * Used to connect to get session to make alterations to the database. 
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Saves the Accounts object account to the database.
	 * @param account - the account to be saved.
	 */
	@Override
	public void saveAccount(Accounts account) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(account);
	}

	/**
	 * Returns a list of the accounts objects contained in the database.
	 * @return
	 */
	@Override
	public List<Accounts> getAccounts() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =  currentSession.createQuery("from accounts order by organisation", Accounts.class);
		
		return theQuery.getResultList();
	}

	/**
	 * Deletes the account specified by the variable accountId.
	 * @param accountId - the id of the account to be deleted.
	 */
	@Override
	public void deleteAccount(int accountId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Accounts temp = currentSession.get(Accounts.class, accountId);
		currentSession.delete(temp);
	}

	/**
	 * Returns the account specified by the accountId variable.
	 * @param accountId - the id of the account to be returned.
	 */
	@Override
	public Accounts getAccount(int accountId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Accounts temp = currentSession.get(Accounts.class, accountId);
		return temp;
	}

}

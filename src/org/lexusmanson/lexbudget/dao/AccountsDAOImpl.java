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
	public void saveAccount(Accounts account, String name) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		if(account.getUsername() == null) {
			account.setUsername(name);
		} else if (!account.getUsername().equals(name)) {
			// TODO: implement exception
		}
		
		currentSession.saveOrUpdate(account);
	}

	/**
	 * Returns a list of the accounts objects contained in the database.
	 * @return
	 */
	@Override
	public List<Accounts> getAccounts(String user) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Accounts> theQuery =  currentSession.createQuery("from accounts where user=:var order by organisation", Accounts.class);
		theQuery.setParameter("var", user);
		return theQuery.getResultList();
	}

	/**
	 * Deletes the account specified by the variable accountId.
	 * @param accountId - the id of the account to be deleted.
	 */
	@Override
	public void deleteAccount(int accountId, String user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Accounts temp = currentSession.get(Accounts.class, accountId);
		
		if(temp.getUsername().equals(user)) {
			currentSession.delete(temp);
		}else {
			// TODO: implement exception
		}
		
		
	}

	/**
	 * Returns the account specified by the accountId variable.
	 * @param accountId - the id of the account to be returned.
	 */
	@Override
	public Accounts getAccount(int accountId, String name) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Accounts> theQuery = currentSession.createQuery("FROM accounts WHERE id=:accountId AND user=:name", Accounts.class);
		
		theQuery.setParameter("accountId", accountId);
		theQuery.setParameter("name", name);
		
		List<Accounts> accList = theQuery.getResultList();
		
		if(accList.size() != 1) {
			//TODO: Add exception here!
		}
		
		return accList.get(0);
		
		//Accounts temp = currentSession.get(Accounts.class, accountId);
		//return temp;
	}

}

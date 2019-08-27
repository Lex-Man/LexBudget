package org.lexusmanson.lexbudget.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.controller.exception.NameMismatchException;
import org.lexusmanson.lexbudget.dao.AccountsDAOImpl;
import org.lexusmanson.lexbudget.dao.exception.AccountReturnException;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountsDAOImplTest {
	
	static String name;
	static String wrongName;
	
	static Accounts newAccount;
	static Accounts updateAccount;
	
	static List<Transactions> newAccountTransactions;
	static List<Transactions> updateAccountTransactions;
	static Transactions transaction;
	
	@InjectMocks
	static AccountsDAOImpl testClass;
	
	@Mock
	SessionFactory sessionFactory;
	
	@Mock
	Session session;
	
	@Mock
	Query<Accounts> query;
	
	@Mock
	Accounts mockAccount;
	
	@BeforeAll
	static void setup() {
		name = "Dave";
		wrongName = "Chaz";
		
		newAccountTransactions = new ArrayList<>();	
		updateAccountTransactions = new ArrayList<>();
		
		transaction = new Transactions(1, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 15.23, updateAccount, 63.08);

		updateAccountTransactions.add(transaction);
		
		newAccount = new Accounts("Chelsea Building Society", "" ,"Savings", 0, newAccountTransactions);
		Transactions transaction = new Transactions(1, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 15.23, updateAccount, 63.08);
		
		updateAccount = new Accounts("Chelsea Building Society", "Dave" ,"Savings", 47.85, updateAccountTransactions);

		testClass = new AccountsDAOImpl();
		
		
	}
	
	/*
	 * The below tests are for the SaveAccount method
	 */
	
	@Test
	void newUserTestSaveAccount() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		testClass.saveAccount(newAccount, name);
		verify(sessionFactory,times(1)).getCurrentSession();
		verify(session, times(1)).saveOrUpdate(newAccount);
	}
	
	@Test
	void updateUserTestSaveAccount() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		testClass.saveAccount(updateAccount, name);
		verify(sessionFactory,times(1)).getCurrentSession();
		verify(session, times(1)).saveOrUpdate(updateAccount);
	}
	
	@Test
	void nameMismatchSaveAccount() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		
		NameMismatchException exc = assertThrows(NameMismatchException.class, 
							() -> testClass.saveAccount(updateAccount, wrongName), 
							"Expected saveAccount to throw NameMismatchException but it didn't.");
		
	}
	
	/*
	 * The below tests are for the getAccounts method
	 */
	
	@Test
	void noAccountsGetAccounts() {
		
		List<Accounts> mockResults = new ArrayList<>();
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("from accounts where user=:var order by organisation", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResults);
		List<Accounts> results = testClass.getAccounts(name);
		assertEquals(mockResults, results);
		assertEquals(0, results.size());
		
	}
	
	@Test
	void oneAccountGetAccounts() {
		List<Accounts> mockResults = new ArrayList<>();
		mockResults.add(updateAccount);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("from accounts where user=:var order by organisation", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResults);
		List<Accounts> results = testClass.getAccounts(name);
		assertEquals(mockResults, results);
		assertEquals(1, results.size());
	}
	
	@Test 
	void threeAccountGetAccounts(){
		List<Accounts> mockResults = new ArrayList<>();
		
		Accounts account1 = new Accounts("Chelsea Building Society", "1" ,"Savings", 0, newAccountTransactions);
		Accounts account2 = new Accounts("Chelsea Building Society", "2" ,"Savings", 0, newAccountTransactions);
		Accounts account3 = new Accounts("Chelsea Building Society", "3" ,"Savings", 0, newAccountTransactions);

		mockResults.add(account1);
		mockResults.add(account2);
		mockResults.add(account3);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("from accounts where user=:var order by organisation", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(mockResults);
		List<Accounts> results = testClass.getAccounts(name);
		assertEquals(mockResults, results);
		assertEquals(3, results.size());
	}
	
	/*
	 * Below is the tests for the deleteAccount method
	 */
	
	@Test
	void deleteAccountTest() {
		
		int id = 1;
		String name = "Dave";
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(Accounts.class, id)).thenReturn(mockAccount);
		when(mockAccount.getUsername()).thenReturn("Dave");
		
		testClass.deleteAccount(id, name);
		verify(session, times(1)).delete(mockAccount);
	}
	
	@Test
	void deleteAccountUserMismatchTest() {
		
		int id = 1;
		String name = "Chaz";
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(Accounts.class, id)).thenReturn(mockAccount);
		when(mockAccount.getUsername()).thenReturn("Dave");
		
		NameMismatchException exc = assertThrows(NameMismatchException.class, 
				() -> testClass.deleteAccount(id, name), 
				"Expected saveAccount to throw NameMismatchException but it didn't.");
	}
	
	/*
	 * Below is the tests for the getAccount method
	 */
	
	@Test
	void getAccountTest() {
		
		Accounts shouldReturn = new Accounts ("Chelsea Building Society", "Dave" ,"Savings", 0, updateAccountTransactions);
		
		int id = 1;
		String name = "Dave";
		List<Accounts> resultsList = new ArrayList<>();
		resultsList.add(shouldReturn);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("FROM accounts WHERE id=:accountId AND user=:name", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(resultsList);
		
		Accounts result = testClass.getAccount(id, name);
		
		assertEquals(shouldReturn, result);
	}
	
	@Test
	void getAccountOverErrorTest() {
		
		Accounts shouldReturn = new Accounts ("Chelsea Building Society", "Dave" ,"Savings", 0, updateAccountTransactions);
		Accounts shouldReturn2 = new Accounts ("Fungus Boogie Man Bank", "Dave" ,"Daily", 1, updateAccountTransactions);

		int id = 1;
		String name = "Dave";
		List<Accounts> resultsList = new ArrayList<>();
		resultsList.add(shouldReturn);
		resultsList.add(shouldReturn2);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("FROM accounts WHERE id=:accountId AND user=:name", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(resultsList);
		
		AccountReturnException exc = assertThrows(AccountReturnException.class, 
				() -> testClass.getAccount(id, name), 
				"Expected getAccount to throw getAccountOverError but it didn't.");
		
	}
	
	@Test
	void getAccountUnderErrorTest() {
		
		Accounts shouldReturn = new Accounts ("Chelsea Building Society", "Dave" ,"Savings", 0, updateAccountTransactions);
		
		int id = 1;
		String name = "Dave";
		List<Accounts> resultsList = new ArrayList<>();
		//resultsList.add(shouldReturn);
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("FROM accounts WHERE id=:accountId AND user=:name", Accounts.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(resultsList);
		
		AccountReturnException exc = assertThrows(AccountReturnException.class, 
				() -> testClass.getAccount(id, name), 
				"Expected getAccount to throw AccountReturnException but it didn't.");
		
	}
	
}

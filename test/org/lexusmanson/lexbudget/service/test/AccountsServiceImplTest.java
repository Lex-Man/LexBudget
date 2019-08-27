package org.lexusmanson.lexbudget.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.dao.AccountsDAO;
import org.lexusmanson.lexbudget.dao.TransactionsDAO;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.lexusmanson.lexbudget.service.AccountsServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountsServiceImplTest {

	@InjectMocks
	AccountsServiceImpl testClass;
	
	@Mock
	AccountsDAO accountDAO;
	
	@Mock
	TransactionsDAO transactionDAO;
	
	String name = "Hugh";
	
	int id = 5;
	int accId = 5;
	
	@BeforeAll
	static void setup(){
		
	}
	
	@Test
	void saveAccountTest() {
		Accounts input = new Accounts();
		testClass.saveAccount(input, name);
		verify(accountDAO ,times(1)).saveAccount(input, name);
	}
	
	@Test
	void getAccountsTest() {
		testClass.getAccounts(name);
		
		Accounts newAccount = new Accounts("Chelsea Building Society", "" ,"Savings", 0, null);
		
		List<Accounts> input = new ArrayList<>();
		input.add(newAccount);
		
		when(accountDAO.getAccounts(name)).thenReturn(input);
		
		List<Accounts> result = testClass.getAccounts(name); 
		assertEquals(input, result);
	}
	
	@Test
	void deleteAccountTest() {
		testClass.DeleteAccount(id, name);
		verify(accountDAO,times(1)).deleteAccount(id, name);
		
	}
	
	@Test
	void getAccountTest() {
		
		Accounts newAccount = new Accounts("Chelsea Building Society", "" ,"Savings", 0, null);
		
		when(accountDAO.getAccount(id,name)).thenReturn(newAccount);
		
		Accounts result = testClass.getAccount(id, name);
		assertEquals(newAccount, result);
	}
	
	@Test
	void saveTransactionTest(){
		Accounts updateAccount = new Accounts("Chelsea Building Society", "Hugh" ,"Savings", 47.85, null);
		updateAccount.setId(5);
		Transactions transaction = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 15.23, updateAccount, 63.08);
		
		List<Transactions> transactions = new ArrayList<>();
		transactions.add(transaction);
		updateAccount.setTransactions(transactions);
		
		when(transactionDAO.getTransactions(id, name)).thenReturn(transactions);
		when(accountDAO.getAccount(id, name)).thenReturn(updateAccount);
		
		testClass.saveTransaction(transaction, name);
		verify(transactionDAO, times(1)).saveTransaction(transaction, name);
	}
	
	@Test
	void getTransactions() {
		
		Transactions transaction = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 15.23, null, 63.08);
		
		List<Transactions> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		when(transactionDAO.getTransactions(id, name)).thenReturn(transactions);
		
		List<Transactions> results = testClass.getTransactions(id, name);
		
		assertEquals(transactions, results);
		
	}
	
	@Test
	void deleteTransactionTest() {
		Accounts updateAccount = new Accounts("Chelsea Building Society", "Hugh" ,"Savings", 47.85, null);
		updateAccount.setId(5);
		Transactions transaction = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 15.23, updateAccount, 63.08);
		
		List<Transactions> transactions = new ArrayList<>();
		transactions.add(transaction);
		updateAccount.setTransactions(transactions);
		
		when(transactionDAO.getTransactions(id, name)).thenReturn(transactions);
		when(accountDAO.getAccount(id, name)).thenReturn(updateAccount);
		
		testClass.deleteTransaction(id,accId, name);
		verify(transactionDAO, times(1)).deleteTransaction(accId, name);
	}
	
	@Test
	void getTransactionTest() {
		
		Transactions trans = new Transactions();
		
		when(transactionDAO.getTransaction(id, name)).thenReturn(trans);
		
		Transactions result = testClass.getTransaction(id, name);
		assertEquals(trans, result);
	}
	
	@Test 
	void updateTransactionsTest(){
		
		Accounts updateAccount = new Accounts("Chelsea Building Society", "Hugh" ,"Savings", 47.85, null);
		updateAccount.setId(5);
		
		List<Transactions> starting = new ArrayList<>();
		
		Transactions tran1 = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 10.00, updateAccount, 63.08);
		Transactions tran2 = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 10.00, updateAccount, 63.08);		
		Transactions tran3 = new Transactions(5, "Chad", "Biscuits", "N/A", "Food",
				LocalDate.now(), 10.00, updateAccount, 63.08);
		
		starting.add(tran1);
		starting.add(tran2);
		starting.add(tran3);
				
		when(accountDAO.getAccount(id, name)).thenReturn(updateAccount);
		when(transactionDAO.getTransactions(id, name)).thenReturn(starting);
		
		try {
			Method method = testClass.getClass().getDeclaredMethod("updateTransactions", int.class , String.class);
			method.setAccessible(true);
			method.invoke(testClass, id, name);
		}catch (NoSuchMethodException exc) {
			System.out.println("Throw No Such Method ");
		} catch (InvocationTargetException exc) {
			System.out.println("Target Exception Thrown");
		} catch (IllegalAccessException exc) {
			System.out.println("Illegal access thrown");
		}
	
		double sum = 0;
		for(Transactions t : starting) {
			sum += t.getAmount();
			assertEquals(sum, t.getBalance());
			
		}
		
	}
	
}

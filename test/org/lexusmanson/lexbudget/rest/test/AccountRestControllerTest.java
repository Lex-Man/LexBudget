package org.lexusmanson.lexbudget.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.rest.AccountRestController;
import org.lexusmanson.lexbudget.rest.error.AccountNotFoundException;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountRestControllerTest {

	String user = "Bob";
	
	@InjectMocks
	static AccountRestController testClass;
	
	@Mock
	Principal mockPrincipal;
	
	@Mock
	AccountsService mockService;
	
	@BeforeAll
	static void setup() {
		testClass = new AccountRestController();
	}
	
	/*
	 * the below tests are for the getAccounts method.
	 */
	
	@Test
	void getAccountsTest() {
		
		List<Accounts> users = new ArrayList<>();
		
		when(mockPrincipal.getName()).thenReturn(user);
		when(mockService.getAccounts(user)).thenReturn(users);
		
		List<Accounts> results = testClass.getAccounts(mockPrincipal);
		
		assertEquals(users, results);
	}
	
	/*
	 * the below tests are for the getAccount method.
	 */
	
	@Test
	void getAccountTest() {
		int id = 1;
		Accounts result = new Accounts();
		
		when(mockPrincipal.getName()).thenReturn(user);
		when(mockService.getAccount(1, user)).thenReturn(result);

		Accounts returnedValue = testClass.getAccount(id,mockPrincipal); 
		
		assertEquals(result,returnedValue);
		
	}
	
	@Test
	void getAccountNotFoundTest() {
		
		int id = 1;
		
		when(mockPrincipal.getName()).thenReturn(user);
		when(mockService.getAccount(1, user)).thenReturn(null);
				
		AccountNotFoundException exc = assertThrows(AccountNotFoundException.class, 
				() -> testClass.getAccount(id, mockPrincipal), 
				"Expected getAccount to throw AccountNotFoundException but it didn't.");
		
	}
	
	/*
	 * The below tests are for the addAccount method
	 */
	
	@Test
	void addAccountTest() {
		
		Accounts account = new Accounts();
		
		when(mockPrincipal.getName()).thenReturn(user);
		
		Accounts resultAccount = testClass.addAccount(account, mockPrincipal);
		assertEquals(account, resultAccount);
		
	}
	
	/*
	 * The below tests are for the updateAccount method
	 */
	@Test
	void updateAccountTest() {
		
		Accounts account = new Accounts();
		
		when(mockPrincipal.getName()).thenReturn(user);
		
		Accounts resultAccount = testClass.addAccount(account, mockPrincipal);
		assertEquals(account, resultAccount);
		
	}
	
	/*
	 * The below tests are for the deleteCustomer method
	 */
	@Test
	void deleteCustomerTest() {
		
		int id = 1;
		Accounts account = new Accounts();
		
		when(mockPrincipal.getName()).thenReturn(user);
		when(mockService.getAccount(id, user)).thenReturn(account);
		
		String results = testClass.deleteCustomer(id, mockPrincipal);
		assertEquals("Deleted customer id - 1", results);
			
	}
	
	@Test
	void deleteCustomerFailTest() {
		
		int id = 1;
		
		when(mockPrincipal.getName()).thenReturn(user);
		when(mockService.getAccount(id, user)).thenReturn(null);
		
		AccountNotFoundException exc = assertThrows(AccountNotFoundException.class, 
				() -> testClass.deleteCustomer(id, mockPrincipal), 
				"Expected deleteCustomer to throw getAccountOverError but it didn't.");
		
	}
		
}

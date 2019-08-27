package org.lexusmanson.lexbudget.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.lexusmanson.lexbudget.rest.TransactionRestController;
import org.lexusmanson.lexbudget.rest.error.TransactionNotFoundException;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.lexusmanson.lexbudget.service.TransactionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TransactionRestControllerTest {

	int id = 1;
	
	String name = "Bob";
	
	@InjectMocks
	static TransactionRestController testClass;
	
	@Mock
	Principal principal;
	
	@Mock
	TransactionService transactionService;
	
	@Mock
	AccountsService accountService;
	
	@BeforeAll
	static void setup() {
		
		testClass = new TransactionRestController();
	}
	
	@Test
	void getTransationTest() {
		
		List<Transactions> results = new ArrayList<>();
		Transactions trans = new Transactions(1, "Chad", "Blah", "Blah", "Blah", LocalDate.now(),
				5.00, null, 42.00);
		
		results.add(trans);
		
		when(principal.getName()).thenReturn(name);
		when(transactionService.getTransactions(id, name)).thenReturn(results);
		List<Transactions> ans = testClass.getTransactions(id, principal);
		
		assertEquals(results, ans);
	}
	
	@Test
	void getTransactionTest() {
		when(principal.getName()).thenReturn(name);
		
		Transactions trans = new Transactions(1, "Chad", "Blah", "Blah", "Blah", LocalDate.now(),
				5.00, null, 42.00);
		
		when(transactionService.getTransaction(4, name)).thenReturn(trans);
		Transactions ans = testClass.getTransaction(id, 4, principal);
		assertEquals(trans, ans);
		
	}
	
	@Test
	void getTransactionErrorTest() {
		
		when(principal.getName()).thenReturn(name);
		
		TransactionNotFoundException exp = assertThrows(TransactionNotFoundException.class, () 
				-> testClass.getTransaction(id, 4, principal),
				"Expected getTransaction to throw TransactionNotFoundException but it didn't");
		
	}
	
	@Test
	void addTransactionTest() {
		
		Transactions passed = new Transactions(1, "Phil", "Blah", "Blah", "Blah", LocalDate.now(),
				92.00, null, 42.00);
		
		List<Transactions> transactions = new ArrayList<>();
		Transactions trans = new Transactions(1, "Chad", "Blah", "Blah", "Blah", LocalDate.now(),
				5.00, null, 42.00);
		
		Accounts account = new Accounts("BlahBlah Bank", "Bob", "Blahing", 14.52, transactions);
		
		when(principal.getName()).thenReturn(name);
		when(accountService.getAccount(id, name)).thenReturn(account);
		
		Transactions result = testClass.updateTransactions(passed, id, principal);
		assertEquals(passed, result);
	}
	
	@Test
	void updateTransationsTest() {
		
		Transactions passed = new Transactions(1, "Phil", "Blah", "Blah", "Blah", LocalDate.now(),
				92.00, null, 42.00);
		
		List<Transactions> transactions = new ArrayList<>();
		Transactions trans = new Transactions(1, "Chad", "Blah", "Blah", "Blah", LocalDate.now(),
				5.00, null, 42.00);
		Accounts account = new Accounts("BlahBlah Bank", "Bob", "Blahing", 14.52, transactions);
		
		when(principal.getName()).thenReturn(name);
		when(principal.getName()).thenReturn(name);
		
		Transactions result = testClass.updateTransactions(passed, id, principal);
		assertEquals(passed, result);
	}
	
	@Test
	void deleteTransactionsTest() {
		String result = "Deleted transaction id - 5";  
		
		Transactions trans = new Transactions(5, "Chad", "Blah", "Blah", "Blah", LocalDate.now(),
				5.00, null, 42.00);
		
		
		when(principal.getName()).thenReturn(name);
		
		String value = testClass.deleteTransactions(id, 5, principal);
		assertEquals(result, value);
	}
}

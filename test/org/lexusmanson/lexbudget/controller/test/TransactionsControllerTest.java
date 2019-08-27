package org.lexusmanson.lexbudget.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.security.Principal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.controller.TransactionsController;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.entity.Transactions;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.lexusmanson.lexbudget.service.TransactionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TransactionsControllerTest {

	int id;
	
	int transId;
	
	@Mock
	Model mockModel;
	
	@Mock
	Principal mockPrincipal;
	
	@Mock
	AccountsService accountsService;
	
	@Mock
	TransactionService transactionService;
	
	@Mock
	Transactions mockTransaction;
	
	@Mock
	BindingResult bindingResult;
	
	@Mock
	Accounts mockAccount;
	
	@Mock
	Validator validator;
	
	@InjectMocks
	static TransactionsController testClass;
	
	@BeforeAll
	static void setup() {
		testClass = new TransactionsController();
	}
	
	/*
	 * Below is the tests for the showAccountTest method
	 */
	
	@Test
	void showAccountTest(){
		String result = testClass.showAccount(id, mockModel, mockPrincipal);
		assertEquals("transactions/main", result);
	}
	
	/*
	 * Below is the tests for the addTransaction method 
	 */
	
	@Test
	void addTransactionTest() {
		String result = testClass.addTransaction(id, mockModel, mockPrincipal);
		assertEquals("transactions/transactionsForm", result);
	}
	
	/*
	 * Below is the tests for the saveTransaction method
	 */
	
	@Test
	void saveTransactionTest() {
		when(bindingResult.hasErrors()).thenReturn(false);
		when(mockTransaction.getAccountsId()).thenReturn(mockAccount);
		when(mockAccount.getId()).thenReturn(1);
		String result = testClass.saveTransaction(mockTransaction, bindingResult, mockPrincipal);
		assertEquals("redirect:/transactions/showFormForAdd?accountId=1", result);
		
	}

	@Test
	void saveTransactionErrorTest() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String result = testClass.saveTransaction(mockTransaction, bindingResult, mockPrincipal);
		assertEquals("transactions/transactionsForm", result);
	}

	/*
	 * Below is the tests for the deleteTransaction method
	 */
	
	@Test
	void deleteTransactionTest() {
		int id = 1;
		String result = testClass.deleteTransaction(id, transId, mockPrincipal);
		assertEquals("redirect:/transactions/showAccount?accountId=1", result);
	}
	
	/*
	 * Below is the tests for the updateForm method
	 */
	
	@Test
	void updateFromTest() {
		String result = testClass.updateForm(transId, id, mockModel, mockPrincipal);
		assertEquals("transactions/transactionsForm", result);
	}
	

}

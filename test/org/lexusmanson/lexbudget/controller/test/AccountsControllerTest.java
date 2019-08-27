package org.lexusmanson.lexbudget.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.security.Principal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.controller.AccountsController;
import org.lexusmanson.lexbudget.entity.Accounts;
import org.lexusmanson.lexbudget.service.AccountsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountsControllerTest {
	
	int id;
	
	@InjectMocks
	static AccountsController testClass;
	
	@Mock
	static AccountsService accountsService;
	
	@Mock
	static Model mockModel;
	
	@Mock
	static Principal mockPrincipal;
	
	@Mock
	Accounts theAccount;
	
	@Mock
	BindingResult bindingResult; 
	
	@Mock
	Principal principal;
	
	@BeforeAll
	static void setup() {
		testClass = new AccountsController();
	}
	
	/*
	 * The below tests are for the main method
	 */
	
	@Test
	void main() {
		
		String result = testClass.main(mockModel, mockPrincipal);
		assertEquals("accounts/main", result);
		
	}
	
	/*
	 * The below tests are for the addAccount method
	 */
	
	@Test
	void addAccountTest() {
		
		String result = testClass.addAccount(mockModel);
		assertEquals("accounts/accountForm", result);
		
	}
	
	/*
	 * The below tests are for the saveAccount method
	 */
	
	@Test
	void saveAccountTest() {
		when(bindingResult.hasErrors()).thenReturn(false);
		String result= testClass.saveAccount(theAccount, bindingResult, principal);
		assertEquals("redirect:/accounts/main", result);
	}
	
	@Test
	void saveAccountErrorsTest() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String result= testClass.saveAccount(theAccount, bindingResult, principal);
		assertEquals("accounts/accountForm", result);
	}
	
	/*
	 * The below are tests for the deleteAccountMethod.
	 */
	
	@Test
	void deleteAccountTest( ) {
		String result = testClass.deleteAccount(id, principal);
		assertEquals("redirect:/accounts/main", result);
	}
	
	/*
	 * The below are tests for showFromForUpdate
	 */
	
	@Test
	void showFormForUpdateTest() {
		String result = testClass.showFormForUpdate(id, mockModel, principal);
		assertEquals("accounts/accountForm", result);
	}
	
	
	
}

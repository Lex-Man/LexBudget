package org.lexusmanson.lexbudget.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.DTO.UserDTO;
import org.lexusmanson.lexbudget.controller.LoginController;
import org.lexusmanson.lexbudget.service.LoginService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LoginControllerTest {

	@Mock
	static LoginService loginService;
	
	@Mock
	Model model;
	
	@Mock
	UserDTO userDTO;
	
	@InjectMocks
	static LoginController testClass;
	
	@BeforeAll
	static void setup() {
		testClass = new LoginController(loginService);
	}
	
	
	/*
	 * The below tests are for the login method 
	 */
	
	@Test
	void loginTest() {
		String result = testClass.login();
		assertEquals("login/login", result);
	}
	
	/*
	 *  The below tests are for the signup method
	 */
	
	@Test
	void signupTest() {
		String result = testClass.signup(model);
		assertEquals("login/signup", result);
	}
	
	/*
	 * The below tests are for the processSignUp method
	 */
	
	@Test
	void processSignUpTest() {
		String result = testClass.processSignUp(userDTO);
		assertEquals("login/success", result);
	}
	
}

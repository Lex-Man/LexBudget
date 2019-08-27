package org.lexusmanson.lexbudget.service.test;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.lexusmanson.lexbudget.DTO.UserDTO;
import org.lexusmanson.lexbudget.dao.LoginDAO;
import org.lexusmanson.lexbudget.service.LoginServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LoginServiceImplTest {

	@Mock
	static LoginDAO loginDAO;
	
	@Mock
	static BCryptPasswordEncoder bCrypt;
	
	@InjectMocks
	static LoginServiceImpl testClass;
	
	static UserDTO userDTO;
	
	@BeforeAll
	static void setup() {
		testClass = new LoginServiceImpl(loginDAO, bCrypt);
		
		userDTO = new UserDTO();
		userDTO.setUsername("Bob");
		userDTO.setPassword("Blah");
	}
	
	@Test
	void signUpTest(){
		when(loginDAO.checkUserExists("Bob")).thenReturn(false);
		when(bCrypt.encode("Blah")).thenReturn("$2y$12$.S9PDWT66p8uv1HEP3EJr.Oj4Ez4nP3nLlH/Jq/G.rynpiWvnOAiG\n");
		
		Boolean result = testClass.signUp(userDTO);
		assertEquals(true, result);
	}
	
	@Test
	void signUpErrorTest() {
		when(loginDAO.checkUserExists("Bob")).thenReturn(true);
		
		Boolean result = testClass.signUp(userDTO);
		assertEquals(false, result);
	}
	
}

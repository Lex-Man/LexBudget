package org.lexusmanson.lexbudget.dao.test;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.lexusmanson.lexbudget.dao.LoginDAOImpl;
import org.lexusmanson.lexbudget.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LoginDAOImplTest {

	String user = "Rob"; 
	
	@Mock
	SessionFactory session;
	
	@Mock
	Session mockSession;
	
	@Mock
	Query<User> mockQuery;
	
	@InjectMocks
	static LoginDAOImpl testClass;
	
	@BeforeAll
	static void setup() {
		testClass = new LoginDAOImpl();
	}
	
	/*
	 * The below tests are for the checkUserExists method.
	 */
	
	@Test
	void checkUserExistsTrueTest() {
		List<User> result = new ArrayList<>();
		User userResult = new User();
		userResult.setUsername("Bob");
		userResult.setPassword("Bob");
		userResult.setEnabled(true);
		
		result.add(userResult);
		
		when(session.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createQuery("from User where username=:var", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(result);
		boolean boo = testClass.checkUserExists(user);
		
		assertEquals(true, boo);
	}
	
	@Test
	void checkUserExistsFalseTest() {
		List<User> result = new ArrayList<>();
		
		when(session.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createQuery("from User where username=:var", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(result);
		boolean boo = testClass.checkUserExists(user);
		
		assertEquals(false, boo);
	}
	
	/*
	 * The below tests are for the addUser method
	 */
	
	@Test
	void checkAddUserTest() {
		
		User user = new User(); 
		when(session.getCurrentSession()).thenReturn(mockSession);
		
		testClass.addUser(user);
				
		verify(mockSession, times(1)).saveOrUpdate(user);
	}
	
}

package org.lexusmanson.lexbudget.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class stringReplacementTest {


	@Autowired
	private SessionFactory sessionFactory;
	
	public stringReplacementTest() {
		
		
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("from Transactions where accountsId=:var");
		theQuery.setParameter("var", 1);
		System.out.println("Query = " + theQuery.getQueryString());
	}
	
	public static void main(String[] args) {
		stringReplacementTest t = new stringReplacementTest();

	}

}

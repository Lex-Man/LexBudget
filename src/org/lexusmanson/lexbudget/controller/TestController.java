package org.lexusmanson.lexbudget.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.lexusmanson.lexbudget.entity.*;
import org.lexusmanson.lexbudget.dao.AccountsDAOImpl;
import org.lexusmanson.lexbudget.dao.TransactionsDAOImpl;

/**
 * Used for test purposes only remove.  DELETE when required
 */

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	AccountsDAOImpl DAO;
	
	@GetMapping("/stringtest")
	@Transactional 
	public String StringTest() {
	
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("from Transactions where id=:var");
		theQuery.setParameter("var", 1);
		List<Transactions> trans = theQuery.getResultList();
		System.out.println(trans.toString());

		session = sessionFactory.getCurrentSession();
		theQuery = session.createQuery("from Transactions where accountsId.id=:var");
		theQuery.setParameter("var", 6);
		trans = theQuery.getResultList();
		System.out.println(trans.toString());
		
		return "redirect:/";
	}
	
	@GetMapping("/accountTest")
	@Transactional
	public String AccountTest() {
		Accounts temp = DAO.getAccount(9, "Lex");
		List<Transactions> aList = temp.getTransactions();
		System.out.println("Account name: " + temp.getOrganisation() + "\n\n\n");
		
		for(Transactions t: temp.getTransactions()) {
			System.out.println("transaction: " + t.getId());
		}
		
		for(Transactions t: aList) {
			System.out.println("transaction: " + t.getId());
		}
		return "redirect:/";
	}
	
}

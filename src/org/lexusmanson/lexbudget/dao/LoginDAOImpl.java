package org.lexusmanson.lexbudget.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.lexusmanson.lexbudget.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean checkUserExists(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where username=:var", User.class);
		theQuery.setParameter("var", username);
		List<User> users = theQuery.getResultList();
		if(users.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void addUser(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(user);
	}

}

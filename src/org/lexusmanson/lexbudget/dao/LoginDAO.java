package org.lexusmanson.lexbudget.dao;

import org.lexusmanson.lexbudget.entity.User;

public interface LoginDAO {

	public boolean checkUserExists(String username);
	
	public void addUser(User user);
	
}

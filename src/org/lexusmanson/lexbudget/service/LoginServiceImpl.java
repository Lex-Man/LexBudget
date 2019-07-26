package org.lexusmanson.lexbudget.service;

import javax.transaction.Transactional;

import org.lexusmanson.lexbudget.DTO.UserDTO;
import org.lexusmanson.lexbudget.dao.LoginDAO;
import org.lexusmanson.lexbudget.entity.Authorities;
import org.lexusmanson.lexbudget.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	private ModelMapper mapper;
	private LoginDAO loginDAO;
	
	@Autowired
	public LoginServiceImpl(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
		this.mapper = new ModelMapper();
	}
	
	@Transactional
	public boolean signUp(UserDTO userDTO) {
		if(loginDAO.checkUserExists(userDTO.getUsername())) {
			return false;
		}
		User user = mapper.map(userDTO, User.class);
		user.setPassword("{noop}" + user.getPassword());
		user.setEnabled(true);
		
		Authorities authority = new Authorities(user, "ROLE_MEMBER");
				
		user.addAuthority(authority);
		
		loginDAO.addUser(user);
		
		return true;
		
	}
	
}

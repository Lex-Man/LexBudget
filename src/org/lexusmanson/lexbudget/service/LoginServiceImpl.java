package org.lexusmanson.lexbudget.service;

import javax.transaction.Transactional;

import org.lexusmanson.lexbudget.DTO.UserDTO;
import org.lexusmanson.lexbudget.dao.LoginDAO;
import org.lexusmanson.lexbudget.entity.Authorities;
import org.lexusmanson.lexbudget.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

	private ModelMapper mapper;
	private LoginDAO loginDAO;
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	public LoginServiceImpl(LoginDAO loginDAO, BCryptPasswordEncoder bCrypt) {
		this.loginDAO = loginDAO;
		this.bCrypt = bCrypt;
		this.mapper = new ModelMapper();
	}
	
	@Transactional
	public boolean signUp(UserDTO userDTO) {
		if(loginDAO.checkUserExists(userDTO.getUsername())) {
			return false;
		}
		User user = mapper.map(userDTO, User.class);
		user.setPassword("{bcrypt}" + bCrypt.encode(user.getPassword()));
		user.setEnabled(true);
		
		Authorities authority = new Authorities(user, "ROLE_MEMBER");
				
		user.addAuthority(authority);
		
		loginDAO.addUser(user);
		
		return true;
		
	}
	
}

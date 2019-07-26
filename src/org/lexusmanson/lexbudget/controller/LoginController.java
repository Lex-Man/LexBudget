package org.lexusmanson.lexbudget.controller;

import org.lexusmanson.lexbudget.DTO.UserDTO;
import org.lexusmanson.lexbudget.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signup", new UserDTO());
		return "login/signup";
	}
	
	@PostMapping("/signup")
	public String processSignUp(@ModelAttribute("signup") UserDTO userDTO) {
		boolean result = loginService.signUp(userDTO);
		return "login/success";
	}
}

package com.fanmovie.fanmovie.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fanmovie.fanmovie.models.User;
import com.fanmovie.fanmovie.repository.UserRepository;

@Controller
public class RegisterUserController {

	@Autowired
	private UserRepository ur;
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(User u) {
		
		ur.save(u);		
		return "formUser";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.GET)
	public String register() {		
		return "formUser";
	}
}

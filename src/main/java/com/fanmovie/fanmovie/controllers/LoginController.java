package com.fanmovie.fanmovie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fanmovie.fanmovie.models.User;
import com.fanmovie.fanmovie.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository repo;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "/home";
	}

	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public String logar(User user) {
		User u = this.repo.Login(user.getEmail(), user.getPassword());

		if (u == null) {
			return "redirect:/";
		}

		return "/home";
	}

	
	
	
}

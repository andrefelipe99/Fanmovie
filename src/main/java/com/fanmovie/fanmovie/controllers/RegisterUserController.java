package com.fanmovie.fanmovie.controllers;

import javax.validation.Valid;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fanmovie.fanmovie.models.User;
import com.fanmovie.fanmovie.repository.UserRepository;

@Controller
public class RegisterUserController {

	@Autowired
	private UserRepository ur;

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(User u, RedirectAttributes attributes) {

		User user = ur.findByEmail(u.getEmail());
		
		if(u.getEmail().trim().isBlank() || u.getPassword().trim().isBlank() || u.getName().trim().isBlank()) {
			attributes.addFlashAttribute("flashMessage", "Campo(s) vazio(s)!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/registerUser";
		}

		if (user != null) {
			attributes.addFlashAttribute("flashMessage", "Email já Existente!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/registerUser";

		}

		ur.save(u);
		return "redirect:/";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public String register() {
		return "formUser";
	}

	@GetMapping("/")
	public String index(Model model, User u) {

		return "/index";
	}

}

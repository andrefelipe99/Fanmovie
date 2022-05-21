package com.fanmovie.fanmovie.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanmovie.fanmovie.resource.RequestMovie;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

@Controller
public class FanmovieController {
	
	@ModelAttribute
	public MovieDb init() {
		return new MovieDb();
	}
	
	/*@GetMapping("/")
	public String test(Model model) {
		
		return "/home";
	}*/
	
	//Função de teste, provavelmente irei apagar depois
	@GetMapping("/test")
	public String goToTest(Model model) {
		
		return "/test";
	}
	
	//Função que faz a pesquisa de um filme
	@PostMapping("/")
	public String searchMovie(@RequestParam( name = "movieName", required = false) String movieName, Model model) {
		
		
		if(movieName.isBlank()) {
			return "exception/emptySearch";
		}else {
			// Função que vai fazer a pesquisa de acordo com o nome do filme que o usuario
			// deu
			MovieResultsPage resultSearch = new RequestMovie().searchMovieByName(movieName);

			List<MovieDb> movieList = resultSearch.getResults();

			model.addAttribute("movieList", movieList);
			// System.out.println("Post realizado " + resultSearch.getResults().get(0));

			return "/showResult";
		}
	}
	
	//decidindo se irei realmente precisar dessa função
	/*@GetMapping("/showResult")
	public String showResult(Model model) {
		
		return "/showResult";
	}*/

}

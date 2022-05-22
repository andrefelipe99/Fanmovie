package com.fanmovie.fanmovie.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanmovie.fanmovie.models.Movie;
import com.fanmovie.fanmovie.repository.MovieRepository;
import com.fanmovie.fanmovie.resource.RequestMovie;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

@Controller
public class FanmovieController {
	
	@Autowired
	private MovieRepository mr;
	
	private List<MovieDb> movieList;

	@ModelAttribute
	public MovieDb init() {
		return new MovieDb();
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "/home";
	}
	
	// Função que faz a 1pesquisa de um filme
	@PostMapping("/showResult")
	public String searchMovie(@RequestParam(name = "movieName", required = false) String movieName, Model model) {

		if (movieName.isBlank()) {
			return "exception/emptySearch";
		} else {
			// Função que vai fazer a pesquisa de acordo com o nome do filme que o usuario
			// deu
			MovieResultsPage resultSearch = new RequestMovie().searchMovieByName(movieName);

			movieList = resultSearch.getResults();

			model.addAttribute("movieList", movieList);
			// System.out.println("Post realizado " + resultSearch.getResults().get(0));

			return "/showResult";
		}
	}

	// adicionar filme como "completo"
	@RequestMapping(value = "/addCompleteMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMovieComplete(@PathVariable("codigo") int codigo) {
		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Completo")) {
				existe = true;
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Completo", (long) 1);
			mr.save(movie);
		}	
				
		return "redirect:/showResult";
	}

	// adicionar filme como "planejo assistir"
	@RequestMapping(value = "/PlanAssistirMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMoviePlanAssistir(@PathVariable("codigo") int codigo) {
		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Planejo Assistir")) {
				existe = true;
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Planejo Assistir", (long) 1);
			mr.save(movie);
		}
		
		return "redirect:/showResult";
	}

	// adicionar filme como "favorito"
	@RequestMapping(value = "/favoritoMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMovieFavorito(@PathVariable("codigo") int codigo) {

		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Favorito")) {
				existe = true;
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Favorito", (long) 1);
			mr.save(movie);
		}			
		
		return "redirect:/showResult";
	}

	@RequestMapping("/removerMovie/{codigo}")
	public String removerMovie(@PathVariable("codigo") int codigo) {
		// falta criar a função de crud para excluir
		Movie m = mr.findById((long)codigo);
		
		if(m.getCategory().equalsIgnoreCase("Favorito")) {
			mr.delete(m);
			return "redirect:/favorito";
		} else if(m.getCategory().equalsIgnoreCase("Completo")) {
			mr.delete(m);
			return "redirect:/completo";
		} else if(m.getCategory().equalsIgnoreCase("Planejo Assistir")) {
			mr.delete(m);
			return "redirect:/planAssistir";
		}
		return "redirect:/home";
	}

	// decidindo se irei realmente precisar dessa função
	@GetMapping("/showResult")
	public String showResult(Model model) {
		if(this.movieList != null) {
			model.addAttribute("movieList", movieList);
		}
		return "/showResult";
	}
	
	@GetMapping("/planAssistir")
	public String telaPlanAssistir(Model model) {
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
		
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Planejo Assistir")) {
				moviesCompleteList.add(m);
			}
		}
		model.addAttribute("movieList",moviesCompleteList);
		return "/planAssistir";
	}
	
	@GetMapping("/completo")
	public String telacompleto(Model model) {
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
		
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Completo")) {
				moviesCompleteList.add(m);
			}
		}
		model.addAttribute("movieList",moviesCompleteList);
		return "/completo";
	}
	
	@GetMapping("/favorito")
	public String telaFavorito(Model model) {
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
		
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Favorito")) {
				moviesCompleteList.add(m);
			}
		}
		model.addAttribute("movieList",moviesCompleteList);
		return "/favorito";
	}
}

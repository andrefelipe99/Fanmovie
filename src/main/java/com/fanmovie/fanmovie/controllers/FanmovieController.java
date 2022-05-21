package com.fanmovie.fanmovie.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanmovie.fanmovie.models.Movie;
import com.fanmovie.fanmovie.resource.RequestMovie;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

@Controller
public class FanmovieController {

	@ModelAttribute
	public MovieDb init() {
		return new MovieDb();
	}

	// Função que faz a pesquisa de um filme

	@PostMapping("/")
	public String searchMovie(@RequestParam(name = "movieName", required = false) String movieName, Model model) {

		if (movieName.isBlank()) {
			return "exception/emptySearch";
		} else {
			// Função que vai fazer a pesquisa de acordo com o nome do filme que o usuario
			// deu
			MovieResultsPage resultSearch = new RequestMovie().searchMovieByName(movieName);

			List<MovieDb> movieList = resultSearch.getResults();

			model.addAttribute("movieList", movieList);
			// System.out.println("Post realizado " + resultSearch.getResults().get(0));

			return "/showResult";
		}
	}

	// adicionar filme como "completo"
	@RequestMapping("/addCompleteMovie/{codigo}")
	public void adicionarMovieComplete(@PathVariable("codigo") int codigo) {

		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);

		Movie movie = new Movie(movieDb, "Completo", (long) 1);
		new MovieController().saveMovie(movie);
	}

	// adicionar filme como "planejo assistir"
	@RequestMapping("/PlanAssistirMovie/{codigo}")
	public void adicionarMoviePlanAssistir(@PathVariable("codigo") int codigo) {

		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);

		Movie movie = new Movie(movieDb, "Planejo Assistir", (long) 1);
		new MovieController().saveMovie(movie);
	}

	// adicionar filme como "favorito"
	@RequestMapping("/favoritoMovie/{codigo}")
	public void adicionarMovieFavorito(@PathVariable("codigo") int codigo) {

		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);

		Movie movie = new Movie(movieDb, "Favorito", (long) 1);
		new MovieController().saveMovie(movie);
	}

	@RequestMapping("/removerMovie/{codigo}")
	public void removerMovieComplete(@PathVariable("codigo") int codigo) {
		// falta criar a função de crud para excluir

	}

	// decidindo se irei realmente precisar dessa função
	@GetMapping("/showResult")
	public String showResult(Model model) {

		return "/showResult";
	}

}

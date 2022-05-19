package com.fanmovie.fanmovie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fanmovie.fanmovie.models.Movie;
import com.fanmovie.fanmovie.repository.MovieRepository;
/*
 * Primeiro precisa salvar um objeto filme vindo da api
 * Depois fazer o html dos filmes salvos ou listas
 * 
 */
@Controller
public class MovieController {

	@Autowired
	private MovieRepository mr;
	
	@GetMapping("/movies")
	public List<Movie> listMovies() {
		return mr.findAll();
	}
	
	@GetMapping("/movies/{id}")
	public Movie listMovieUnique(@PathVariable(value="id") long id) {
		return mr.findById(id);
	}
	
	@PostMapping("/movie")
	public Movie saveMovie(@RequestBody Movie m) {
		return mr.save(m);
	}
}

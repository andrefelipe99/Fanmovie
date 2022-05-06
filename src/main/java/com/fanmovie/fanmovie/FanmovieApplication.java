package com.fanmovie.fanmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;

@SpringBootApplication
public class FanmovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanmovieApplication.class, args);
		
		//System.out.println("Printar algo");
		
		//TmdbMovies movies = new TmdbApi("a2f88bffa883d520f4fbd55a6045d697").getMovies();
		//MovieDb movie = movies.getMovie(5353, "pt-BR");
		
		//System.out.println(movie.getOriginalTitle());
		
		
	}

}
